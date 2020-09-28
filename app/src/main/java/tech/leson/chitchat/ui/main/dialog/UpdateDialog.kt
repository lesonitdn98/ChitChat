package tech.leson.chitchat.ui.main.dialog

import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.DialogUpdateBinding
import tech.leson.chitchat.ui.base.BaseFragmentDialog
import tech.leson.chitchat.ui.main.MainActivity
import tech.leson.chitchat.ui.password.PasswordActivity
import tech.leson.chitchat.ui.update.UpdateActivity
import tech.leson.chitchat.ui.username.UsernameActivity
import javax.inject.Inject

class UpdateDialog :
    BaseFragmentDialog<DialogUpdateBinding, UpdateDialogNavigator, UpdateDialogViewModel>(),
    UpdateDialogNavigator {

    companion object {
        private var instance: UpdateDialog? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: UpdateDialog().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.dialog_update
    override val viewModel: UpdateDialogViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(UpdateDialogViewModel::class.java)
        }

    override fun init() {
        viewModel.setNavigator(this)
    }

    override fun openUpdateInformation() {
        val intent = UpdateActivity.getIntent(activity!!)
        intent.putExtra("activity", MainActivity.ACTIVITY)
        activity?.startActivityForResult(intent, MainActivity.REQUEST_CODE)
        dismiss()
    }

    override fun openChangeUsername() {
        activity?.startActivityForResult(UsernameActivity.getIntent(activity!!), MainActivity.REQUEST_CODE)
        dismiss()
    }

    override fun openChangePassword() {
        activity?.startActivity(PasswordActivity.getIntent(activity!!))
        dismiss()
    }
}
