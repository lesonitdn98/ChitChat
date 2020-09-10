package me.lesonnnn.chitchat.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivitySplashBinding
import me.lesonnnn.chitchat.ui.base.BaseActivity
import me.lesonnnn.chitchat.ui.login.LoginActivity
import me.lesonnnn.chitchat.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashNavigator, SplashViewModel>(),
    SplashNavigator {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(SplashViewModel::class.java)
        }

    override fun handleError(throwable: Throwable?) {
        //
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        startActivity(LoginActivity.getIntent(this))
        finish()
    }

    override fun init() {}

    override fun addAnimTransition() {}

}
