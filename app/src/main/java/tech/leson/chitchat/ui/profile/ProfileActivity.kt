package tech.leson.chitchat.ui.profile

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_profile.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivityProfileBinding
import tech.leson.chitchat.ui.base.BaseActivity
import javax.inject.Inject

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileNavigator, ProfileViewModel>(),
    ProfileNavigator {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, ProfileActivity::class.java).also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_profile
    override val viewModel: ProfileViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(ProfileViewModel::class.java)
        }

    override fun addAnimTransition() {}

    override fun init() {
        viewModel.setNavigator(this)
        viewModel.getUserInfo()
    }

    override fun onBack() {
        onBackPressed()
    }

    override fun getUserFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
