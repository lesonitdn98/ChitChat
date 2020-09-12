package me.lesonnnn.chitchat.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.Explode
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_search.*
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivitySearchBinding
import me.lesonnnn.chitchat.ui.base.BaseActivity
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchNavigator, SearchViewModel>(), SearchNavigator {

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

    override fun init() {}

    override fun addAnimTransition() {
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Explode()
        }
    }

    override fun closeSearchView() {
        hideKeyboard()
        onBackPressed()
    }
}
