package tech.leson.chitchat.ui.splash

import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivitySplashBinding
import tech.leson.chitchat.ui.base.BaseActivity
import tech.leson.chitchat.ui.login.LoginActivity
import tech.leson.chitchat.ui.main.MainActivity
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

    override fun openMainActivity() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }

    override fun openLoginActivity() {
        startActivity(LoginActivity.getIntent(this))
        finish()
    }

    override fun init() {
        viewModel.setNavigator(this)
        viewModel.checkUserAsLogin()
    }

    override fun addAnimTransition() {}

}
