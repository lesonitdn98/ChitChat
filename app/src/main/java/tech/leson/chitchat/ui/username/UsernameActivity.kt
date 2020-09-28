package tech.leson.chitchat.ui.username

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_username.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivityUsernameBinding
import tech.leson.chitchat.ui.base.BaseActivity
import tech.leson.chitchat.utils.NetworkUtils
import javax.inject.Inject

class UsernameActivity :
    BaseActivity<ActivityUsernameBinding, UsernameNavigator, UsernameViewModel>(),
    UsernameNavigator {

    companion object {
        const val RESULT_CODE = 5555
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, UsernameActivity::class.java).also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_username
    override val viewModel: UsernameViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(UsernameViewModel::class.java)
        }

    override fun addAnimTransition() {}

    override fun init() {
        viewModel.setNavigator(this)
    }

    override fun onSave() {
        if (NetworkUtils.isNetworkConnected(this)) {
            viewModel.changeUsername(edtUsername.text.toString().trim())
        } else {
            Toast.makeText(this, getText(R.string.network_not_connected), Toast.LENGTH_SHORT).show()
        }
    }

    override fun changeSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        setResult(RESULT_CODE)
        finish()
    }

    override fun changeFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onBack() {
        hideKeyboard()
        finish()
    }

}
