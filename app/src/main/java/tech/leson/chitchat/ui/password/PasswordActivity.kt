package tech.leson.chitchat.ui.password

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_password.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivityPasswordBinding
import tech.leson.chitchat.ui.base.BaseActivity
import tech.leson.chitchat.utils.NetworkUtils
import javax.inject.Inject

class PasswordActivity :
    BaseActivity<ActivityPasswordBinding, PasswordNavigator, PasswordViewModel>(),
    PasswordNavigator {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, PasswordActivity::class.java).also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_password
    override val viewModel: PasswordViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(PasswordViewModel::class.java)
        }

    override fun addAnimTransition() {}

    override fun init() {
        viewModel.setNavigator(this)
    }

    override fun onSave() {
        if (NetworkUtils.isNetworkConnected(this)) {
            if (edtNewPass.text.toString() == edtRetypePass.text.toString()) {
                viewModel.changePassword(edtOldPass.text.toString().trim(),
                    edtNewPass.text.toString().trim())
            } else Toast.makeText(this, getText(R.string.retype_password_fail), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, getText(R.string.network_not_connected), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onChangePassSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onChangePassFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onBack() {
        hideKeyboard()
        finish()
    }

}
