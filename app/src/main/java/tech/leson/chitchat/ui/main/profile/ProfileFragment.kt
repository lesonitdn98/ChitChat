package tech.leson.chitchat.ui.main.profile

import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.fragment_profile.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.FragmentProfileBinding
import tech.leson.chitchat.ui.base.BaseFragment
import tech.leson.chitchat.ui.login.LoginActivity
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileNavigator, ProfileViewModel>(),
    ProfileNavigator, CompoundButton.OnCheckedChangeListener {

    companion object {
        private var instance: ProfileFragment? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ProfileFragment().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val viewModel: ProfileViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(ProfileViewModel::class.java)
        }

    override fun init() {
        viewModel.setNavigator(this)
        swDarkMode.setOnCheckedChangeListener(this)
        viewModel.getUserInfo()
    }

    override fun getUserFailed(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        swDarkMode.isEnabled = false
        viewModel.setDarkMode(p1)
        if (p1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            swDarkMode.isEnabled = true
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            swDarkMode.isEnabled = true
        }
    }

    override fun signOut() {
        activity?.startActivity(LoginActivity.getIntent(activity!!))
        activity?.finish()
    }

    override fun signOutFail(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}
