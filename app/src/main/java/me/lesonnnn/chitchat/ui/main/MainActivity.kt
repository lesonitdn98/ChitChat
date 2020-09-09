package me.lesonnnn.chitchat.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.content_main.*
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivityMainBinding
import me.lesonnnn.chitchat.ui.base.BaseActivity
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

    @Inject
    lateinit var pageAdapter: MainPageAdapter

    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mTab = TAB.TAB_HOME

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
        pageAdapter.mItemCount = 5
        viewPager.adapter = pageAdapter
        viewPager.currentItem = 0
    }

    override fun init() {}

    override fun onTabSelected(tab: TAB) {
        when (tab) {
            TAB.TAB_HOME -> {
                if (mTab != TAB.TAB_HOME) {
                    mTab = TAB.TAB_HOME
                    viewPager.currentItem = 0
                }
            }
            TAB.TAB_CONTACT -> {
                if (mTab != TAB.TAB_CONTACT) {
                    mTab = TAB.TAB_CONTACT
                    Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show()
                    viewPager.currentItem = 1
                }
            }
            TAB.TAB_QR_CODE -> {
                if (mTab != TAB.TAB_QR_CODE) {
                    mTab = TAB.TAB_QR_CODE
                    Toast.makeText(this, "qr code", Toast.LENGTH_SHORT).show()
                    viewPager.currentItem = 2
                }
            }
            TAB.TAB_GROUP -> {
                if (mTab != TAB.TAB_GROUP) {
                    mTab = TAB.TAB_GROUP
                    Toast.makeText(this, "group", Toast.LENGTH_SHORT).show()
                    viewPager.currentItem = 3
                }
            }
            TAB.TAB_MENU -> {
                if (mTab != TAB.TAB_MENU) {
                    mTab = TAB.TAB_MENU
                    Toast.makeText(this, "menu", Toast.LENGTH_SHORT).show()
                    viewPager.currentItem = 4
                }
            }
        }
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
