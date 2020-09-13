package me.lesonnnn.chitchat.ui.main

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.navigation_footer_main.*
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
        var tabCurrent = TAB.TAB_HOME
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, MainActivity::class.java).also { instance = it }
        }
    }

    private lateinit var mTabs: ArrayList<Fragment>

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var mainTabAdapter: MainTabAdapter

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
        viewModel.setNavigator(this)
        btnIconAppBar.setImageResource(R.drawable.ic_search)
        btnAppBar.visibility = View.VISIBLE
    }


    @Suppress("DEPRECATION")
    override fun init() {
        mTabs = ArrayList()
        mTabs.add(HomeFragment.getInstance())
        mTabs.add(ContactFragment.getInstance())
        mTabs.add(QRFragment.getInstance())
        mTabs.add(GroupFragment.getInstance())
        mTabs.add(MenuFragment.getInstance())

        mainTabAdapter.mTabs = mTabs
        viewTabs.orientation = ORIENTATION_HORIZONTAL
        viewTabs.adapter = mainTabAdapter

        TabLayoutMediator(tabMain, viewTabs) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_chat)
                    viewTabs.currentItem = position
                }
                1 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_contact)
                }
                2 -> {
                    tab.view.isClickable = false
                }
                3 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_user_group)
                }
                4 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_menu)
                    btnAppBar.visibility = View.INVISIBLE
                }
            }
        }.attach()

        tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewTabs.currentItem = tab!!.position
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.green)
                when (tab.position) {
                    0 -> {
                        tabCurrent = TAB.TAB_HOME
                        tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                        btnIconAppBar.setImageResource(R.drawable.ic_search)
                        btnAppBar.visibility = View.VISIBLE
                    }
                    1 -> {
                        tabCurrent = TAB.TAB_CONTACT
                        tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                        btnIconAppBar.setImageResource(R.drawable.ic_add_contact)
                        btnAppBar.visibility = View.VISIBLE
                    }
                    2 -> {
                        tabCurrent = TAB.TAB_QR_CODE
                        btnIconAppBar.setImageResource(R.drawable.ic_scan)
                        btnAppBar.visibility = View.VISIBLE
                    }
                    3 -> {
                        tabCurrent = TAB.TAB_GROUP
                        tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                        btnAppBar.visibility = View.INVISIBLE
                    }
                    4 -> {
                        tabCurrent = TAB.TAB_MENU
                        tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                        btnAppBar.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab!!.position != 2) {
                    val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.textColor)
                    tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        tabMain.getTabAt(0)?.select()
        val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.green)
        tabMain.getTabAt(0)?.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
    }

    override fun openSearchView() {
        val options = ActivityOptions
            .makeSceneTransitionAnimation(this, btnIconAppBar, "btnAppBarTransition")
        startActivity(SearchActivity.getIntent(this), options.toBundle())
        delayBtnOnClick(btnAppBar)
    }

    override fun openAddContactView() {}

    override fun openScanView() {}

    override fun onTabQRCodeClick() {
        tabMain.getTabAt(2)?.select()
    }

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
