package tech.leson.chitchat.ui.update.dialog

import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.DialogAvatarBinding
import tech.leson.chitchat.ui.base.BaseFragmentDialog
import javax.inject.Inject

class AvatarDialog :
    BaseFragmentDialog<DialogAvatarBinding, AvatarDialogNavigator, AvatarDialogViewModel>(),
    AvatarDialogNavigator {

    companion object {
        private var instance: AvatarDialog? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: AvatarDialog().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.dialog_avatar
    override val viewModel: AvatarDialogViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(AvatarDialogViewModel::class.java)
        }

    override fun init() {
        viewModel.setNavigator(this)
    }

}
