package me.lesonnnn.chitchat.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivitySplashBinding
import me.lesonnnn.chitchat.ui.TestActivity
import me.lesonnnn.chitchat.ui.base.BaseActivity
import me.lesonnnn.chitchat.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashNavigator, SplashViewModel<SplashNavigator>>(),
    SplashNavigator {

    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set
    var factory: ViewModelProviderFactory? = null
        @Inject set

    private var mSplashViewModel: SplashViewModel<SplashNavigator>? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel<SplashNavigator>? {
        mSplashViewModel = factory?.let {
            ViewModelProvider(
                this,
                it
            ).get(SplashViewModel::class.java)
        } as SplashViewModel<SplashNavigator>
        return mSplashViewModel
    }

    override fun handleError(throwable: Throwable?) {
        //
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(TestActivity.getIntent(this))
        finish()
    }

}
