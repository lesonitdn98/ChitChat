package tech.leson.chitchat.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_search.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivitySearchBinding
import tech.leson.chitchat.model.User
import tech.leson.chitchat.ui.base.BaseActivity
import tech.leson.chitchat.ui.search.adapter.SearchAdapter
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchNavigator, SearchViewModel>(),
    SearchNavigator {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, SearchActivity::class.java).also {
                instance = it
            }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var searchAdapter: SearchAdapter

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_search
    override val viewModel: SearchViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(SearchViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun init() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = searchAdapter
    }

    override fun onSearch() {
        hideKeyboard()
        if (edtSearch.text.toString().trim() != "") {
            if (isNetworkConnected()) viewModel.search(edtSearch.text.toString().trim())
            else Toast.makeText(
                this,
                getString(R.string.network_not_connected),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onSearched(data: MutableList<User>) {
        searchAdapter.clearData()
        searchAdapter.addData(data)
    }

    override fun onSearchFail(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun closeSearchView() {
        hideKeyboard()
        onBackPressed()
    }
}
