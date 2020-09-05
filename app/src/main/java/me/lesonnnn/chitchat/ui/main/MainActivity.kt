package me.lesonnnn.chitchat.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivityMainBinding
import me.lesonnnn.chitchat.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity :
    BaseActivity<ActivityMainBinding, MainNavigator, MainViewModel<MainNavigator>>(),
    MainNavigator {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, MainActivity::class.java).also { instance = it }
        }
    }

    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set
    var factory: ViewModelProviderFactory? = null
        @Inject set

    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mMainViewModel: MainViewModel<MainNavigator>? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = getViewDataBinding()
        mMainViewModel?.setNavigator(this)
        setUp()
    }

    override fun getViewModel(): MainViewModel<MainNavigator> {
        mMainViewModel = factory?.let {
            ViewModelProvider(
                this,
                it
            ).get(MainViewModel::class.java)
        } as MainViewModel<MainNavigator>
        return mMainViewModel as MainViewModel
    }

    override fun handleError(throwable: Throwable?) {
        // handle error
    }

    private fun setUp() {
    }
}
