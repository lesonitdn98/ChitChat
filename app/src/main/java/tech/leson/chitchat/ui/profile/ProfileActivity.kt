package tech.leson.chitchat.ui.profile

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_profile.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivityProfileBinding
import tech.leson.chitchat.ui.base.BaseActivity
import tech.leson.chitchat.utils.NetworkUtils
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

    override fun init() {
        viewModel.setNavigator(this)
        if (NetworkUtils.isNetworkConnected(this)) {
            viewModel.getUserInfo(intent.getStringExtra("username")!!)
        } else {
            Toast.makeText(this, R.string.network_not_connected, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBack() {
        onBackPressed()
    }

    override fun getUserFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setAvatar(avatar: String) {
        Glide.with(this).load(avatar).centerCrop().into(imvAvatarPerson)
    }

    override fun addContact() {}
}
