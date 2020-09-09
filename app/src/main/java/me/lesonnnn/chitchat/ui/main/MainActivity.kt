package me.lesonnnn.chitchat.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.navigation_header_main.*
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivityMainBinding
import me.lesonnnn.chitchat.ui.base.BaseActivity
import me.lesonnnn.chitchat.ui.main.contact.ContactFragment
import me.lesonnnn.chitchat.ui.main.home.HomeFragment
import javax.inject.Inject

class MainActivity :
    BaseActivity<ActivityMainBinding, MainNavigator, MainViewModel>(),
    MainNavigator, HasAndroidInjector {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, MainActivity::class.java).also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var mActivityMainBinding: ActivityMainBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel?
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(MainViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = getViewDataBinding()
        viewModel?.setNavigator(this)
        addTab(HomeFragment.newInstance())
        btnAppBar.setImageResource(R.drawable.ic_search)
        btnAppBar.visibility = View.VISIBLE
    }

    override fun init() {}

    override fun onTabSelected(tab: TAB) {
        when (tab) {
            TAB.TAB_HOME -> {
                replaceTab(HomeFragment.newInstance())
                btnAppBar.setImageResource(R.drawable.ic_search)
                btnAppBar.visibility = View.VISIBLE
            }
            TAB.TAB_CONTACT -> {
                replaceTab(ContactFragment.newInstance())
                btnAppBar.setImageResource(R.drawable.ic_add_contact)
                btnAppBar.visibility = View.VISIBLE
            }
            TAB.TAB_QR_CODE -> {
                Toast.makeText(this, "qr code", Toast.LENGTH_SHORT).show()
                btnAppBar.setImageResource(R.drawable.ic_scan)
                btnAppBar.visibility = View.VISIBLE
            }
            TAB.TAB_GROUP -> {
                Toast.makeText(this, "group", Toast.LENGTH_SHORT).show()
                btnAppBar.visibility = View.INVISIBLE
            }
            TAB.TAB_MENU -> {
                Toast.makeText(this, "menu", Toast.LENGTH_SHORT).show()
                btnAppBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun addTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.viewTab, fragment).commit()
    }

    private fun replaceTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.viewTab, fragment).commit()
    }

    override fun openSearchView() {
        TODO("Not yet implemented")
    }

    override fun handleError(throwable: Throwable?) {
        // handle error
    }

    override fun androidInjector(): DispatchingAndroidInjector<Any>? {
        return dispatchingAndroidInjector
    }
}
