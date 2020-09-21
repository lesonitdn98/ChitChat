package me.lesonnnn.chitchat.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_login.*
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivityLoginBinding
import me.lesonnnn.chitchat.ui.base.BaseActivity
import me.lesonnnn.chitchat.ui.main.MainActivity
import me.lesonnnn.chitchat.ui.register.RegisterActivity
import me.lesonnnn.chitchat.utils.AppUtils
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginNavigator, LoginViewModel>(),
    LoginNavigator {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, LoginActivity::class.java).also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(LoginViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun addAnimTransition() {
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Explode()
        }
    }

    override fun init() {
    }

    override fun login() {
        val phone = getViewDataBinding().edtPhoneNumber.text.toString().trim()
        val password = getViewDataBinding().edtPassword.text.toString().trim()
        if (viewModel.isEmailAndPasswordValid(phone, password)) {
            if (isNetworkConnected()) viewModel.login(phone, password)
            else Toast.makeText(
                this,
                getString(R.string.network_not_connected),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onLoginSuccess() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }

    override fun onLoginFailed(mess: String) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateAccount() {
        AppUtils.delayBtnOnClick(btnCreateAccount)
        startActivity(RegisterActivity.getIntent(this))
    }
}
