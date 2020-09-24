package tech.leson.chitchat.ui.update

import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_update.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.ActivityUpdateBinding
import tech.leson.chitchat.ui.base.BaseActivity
import tech.leson.chitchat.ui.update.dialog.AvatarDialog
import tech.leson.chitchat.ui.update.popup.DobStatePopup
import tech.leson.chitchat.ui.update.popup.EmailStatePopup
import tech.leson.chitchat.ui.update.popup.GenderPopup
import tech.leson.chitchat.utils.NetworkUtils
import java.util.*
import javax.inject.Inject

class UpdateActivity : BaseActivity<ActivityUpdateBinding, UpdateNavigator, UpdateViewModel>(),
    UpdateNavigator, DatePickerDialog.OnDateSetListener, HasAndroidInjector {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Intent(context, UpdateActivity::class.java).also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var genderPopup: GenderPopup

    @Inject
    lateinit var dobStatePopup: DobStatePopup

    @Inject
    lateinit var emailStatePopup: EmailStatePopup

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_update
    override val viewModel: UpdateViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(UpdateViewModel::class.java)
        }

    override fun addAnimTransition() {}

    override fun init() {
        viewModel.setNavigator(this)
        if (NetworkUtils.isNetworkConnected(this)) {
            viewModel.getInfoUser()
        } else {
            Toast.makeText(this, getText(R.string.network_not_connected), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getAvatar(avatar: String) {
        if (!avatar.isBlank()) {
            Glide.with(this).load(viewModel.user.value?.avatar).centerCrop().into(imvAvatar)
        }
    }

    override fun getUserFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onAvatarDialog() {
        if (supportFragmentManager.findFragmentByTag("Avatar") == null) {
            AvatarDialog.getInstance().show(supportFragmentManager, "Avatar")
        }
    }

    override fun onPopupGender() {
        genderPopup.height = WindowManager.LayoutParams.WRAP_CONTENT
        genderPopup.width = resources.displayMetrics.widthPixels / 3
        genderPopup.isOutsideTouchable = true
        genderPopup.isFocusable = true
        genderPopup.showAsDropDown(edtGender, 2 * resources.displayMetrics.widthPixels / 3
                - resources.getDimensionPixelOffset(R.dimen._40sdp),
            -resources.getDimensionPixelOffset(R.dimen._32sdp))
    }

    override fun onGenderSelected(genderMode: String) {
        edtGender.text = genderMode
        genderPopup.dismiss()
    }

    override fun onDobClick() {
        val now = Calendar.getInstance()
        val dpd: DatePickerDialog = DatePickerDialog.newInstance(this@UpdateActivity, 2000, 1, 1)
        dpd.version = DatePickerDialog.Version.VERSION_2
        dpd.maxDate = now
        dpd.accentColor = ContextCompat.getColor(this, R.color.backgroundColor)
        dpd.show(supportFragmentManager, "BirthdayDialog")
    }

    override fun onPopupDobState() {
        dobStatePopup.height = WindowManager.LayoutParams.WRAP_CONTENT
        dobStatePopup.width = resources.displayMetrics.widthPixels / 4
        dobStatePopup.isOutsideTouchable = true
        dobStatePopup.isFocusable = true
        dobStatePopup.showAsDropDown(stateDob, -2 * resources.displayMetrics.widthPixels / 5
                + 2 * resources.getDimensionPixelOffset(R.dimen._40sdp),
            0)
    }

    override fun onDobStateSelected(state: Int) {
        when (state) {
            0 -> {
                stateDob.setImageResource(R.drawable.ic_lock)
            }
            1 -> {
                stateDob.setImageResource(R.drawable.ic_friends)
            }
            2 -> {
                stateDob.setImageResource(R.drawable.ic_public)
            }
        }
        dobStatePopup.dismiss()
    }

    override fun onPopupEmailState() {
        emailStatePopup.height = WindowManager.LayoutParams.WRAP_CONTENT
        emailStatePopup.width = resources.displayMetrics.widthPixels / 4
        emailStatePopup.isOutsideTouchable = true
        emailStatePopup.isFocusable = true
        emailStatePopup.showAsDropDown(stateEmail, -2 * resources.displayMetrics.widthPixels / 5
                + 2 * resources.getDimensionPixelOffset(R.dimen._40sdp),
            0)
    }

    override fun onEmailStateSelected(state: Int) {
        when (state) {
            0 -> {
                stateDob.setImageResource(R.drawable.ic_lock)
            }
            1 -> {
                stateDob.setImageResource(R.drawable.ic_friends)
            }
            2 -> {
                stateDob.setImageResource(R.drawable.ic_public)
            }
        }
        emailStatePopup.dismiss()
    }

    override fun onEdtBioFocus() {
        edtBio.requestFocus()
        showKeyboard()
    }

    override fun onBack() {
        finish()
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val dayOfBirth = StringBuilder()
        dayOfBirth.append(dayOfMonth).append("-").append(monthOfYear).append("-").append(year)
        btnDob.text = dayOfBirth
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}
