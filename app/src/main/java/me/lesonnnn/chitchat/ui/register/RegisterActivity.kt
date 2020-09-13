package me.lesonnnn.chitchat.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_register.*
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.ActivityRegisterBinding
import me.lesonnnn.chitchat.ui.base.BaseActivity
import me.lesonnnn.chitchat.utils.AppUtils
import javax.inject.Inject

class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterNavigator, RegisterViewModel>(),
    RegisterNavigator {

    companion object {
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

    override fun addAnimTransition() {}

    override fun init() {}

    override fun onBack() {
        hideKeyboard()
        AppUtils.delayBtnOnClick(btnSignIn)
        onBackPressed()
    }

}
