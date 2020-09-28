package tech.leson.chitchat.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_register.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivityRegisterBinding
import tech.leson.chitchat.ui.base.BaseActivity
import tech.leson.chitchat.ui.login.LoginActivity
import tech.leson.chitchat.ui.update.UpdateActivity
import tech.leson.chitchat.utils.AppUtils
import tech.leson.chitchat.utils.NetworkUtils
import javax.inject.Inject


class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterNavigator, RegisterViewModel>(),
    RegisterNavigator {

    companion object {
        const val ACTIVITY = "register"
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, RegisterActivity::class.java).also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_register
    override val viewModel: RegisterViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(RegisterViewModel::class.java)
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
        imvBtnCrAccount.startAnimation(AnimationUtils.loadAnimation(this,
            R.anim.anim_left_to_right_arrow))
    }

    override fun onRegisterSuccess() {
        hideKeyboard()
        val intent = UpdateActivity.getIntent(this)
        intent.putExtra("activity", ACTIVITY)
        startActivity(intent)
        finish()
    }

    override fun onRegisterFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRegister() {
        if (NetworkUtils.isNetworkConnected(this)) {
            when {
                edtFullName.text.isBlank() -> {
                    Toast.makeText(this, getText(R.string.full_name_is_empty), Toast.LENGTH_SHORT)
                        .show()
                }
                edtPhoneNumber.text.isBlank() -> {
                    Toast.makeText(this,
                        getText(R.string.phone_number_is_empty),
                        Toast.LENGTH_SHORT).show()
                }
                !AppUtils.isPhoneNumberValid(edtPhoneNumber.text.toString()) -> {
                    Toast.makeText(this, getText(R.string.invalid_phone_number), Toast.LENGTH_SHORT)
                        .show()
                }
                edtPassword.text.isBlank() -> {
                    Toast.makeText(this, getText(R.string.password_is_empty), Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    viewModel.register(edtFullName.text.toString(),
                        edtPhoneNumber.text.toString(),
                        edtPassword.text.toString())
                }
            }
        } else {
            Toast.makeText(this, getText(R.string.network_not_connected), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBack() {
        hideKeyboard()
        AppUtils.delayBtnOnClick(btnSignIn)
        startActivity(LoginActivity.getIntent(this))
        finish()
    }

}
