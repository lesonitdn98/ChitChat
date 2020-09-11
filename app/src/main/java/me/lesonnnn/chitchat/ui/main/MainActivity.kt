package me.lesonnnn.chitchat.ui.main

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
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
import me.lesonnnn.chitchat.ui.main.group.GroupFragment
import me.lesonnnn.chitchat.ui.main.home.HomeFragment
import me.lesonnnn.chitchat.ui.main.menu.MenuFragment
import me.lesonnnn.chitchat.ui.main.qrcode.QRFragment
import me.lesonnnn.chitchat.ui.search.SearchActivity
import me.lesonnnn.chitchat.utils.AppUtils.Companion.delayBtnOnClick
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
    override val viewModel: MainViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(MainViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        addTab(HomeFragment.getInstance())
        btnIconAppBar.setImageResource(R.drawable.ic_search)
        btnAppBar.visibility = View.VISIBLE
    }

    override fun init() {}

    override fun onTabSelected(tab: TAB) {
        when (tab) {
            TAB.TAB_HOME -> {
                replaceTab(HomeFragment.getInstance())
                btnIconAppBar.setImageResource(R.drawable.ic_search)
                btnAppBar.visibility = View.VISIBLE
            }
            TAB.TAB_CONTACT -> {
                replaceTab(ContactFragment.getInstance())
                btnIconAppBar.setImageResource(R.drawable.ic_add_contact)
                btnAppBar.visibility = View.VISIBLE
            }
            TAB.TAB_QR_CODE -> {
                replaceTab(QRFragment.getInstance())
                btnIconAppBar.setImageResource(R.drawable.ic_scan)
                btnAppBar.visibility = View.VISIBLE
            }
            TAB.TAB_GROUP -> {
                replaceTab(GroupFragment.getInstance())
                btnAppBar.visibility = View.INVISIBLE
            }
            TAB.TAB_MENU -> {
                replaceTab(MenuFragment.getInstance())
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
        val options = ActivityOptions
            .makeSceneTransitionAnimation(this, btnIconAppBar, "btnAppBarTransition")
        startActivity(SearchActivity.getIntent(this), options.toBundle())
        delayBtnOnClick(btnAppBar)
    }

    override fun openAddContactView() {}

    override fun openScanView() {}

    override fun handleError(throwable: Throwable?) {}

    override fun androidInjector(): DispatchingAndroidInjector<Any>? {
        return dispatchingAndroidInjector
    }

    override fun addAnimTransition() {
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Explode()
        }
    }
}
