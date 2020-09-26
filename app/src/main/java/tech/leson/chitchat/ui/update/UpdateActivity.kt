package tech.leson.chitchat.ui.update

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
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
import tech.leson.chitchat.ui.main.MainActivity
import tech.leson.chitchat.ui.register.RegisterActivity
import tech.leson.chitchat.ui.update.dialog.AvatarDialog
import tech.leson.chitchat.ui.update.model.UserUpdate
import tech.leson.chitchat.ui.update.popup.DobStatePopup
import tech.leson.chitchat.ui.update.popup.EmailStatePopup
import tech.leson.chitchat.ui.update.popup.GenderPopup
import tech.leson.chitchat.utils.AppConstants
import tech.leson.chitchat.utils.NetworkUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class UpdateActivity : BaseActivity<ActivityUpdateBinding, UpdateNavigator, UpdateViewModel>(),
    UpdateNavigator, DatePickerDialog.OnDateSetListener, HasAndroidInjector {


    companion object {
        const val RESULT_CODE = 4444

        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
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

    private var userUpdate = UserUpdate()
    private var beforeActivity = ""

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
        if (NetworkUtils.isNetworkConnected(this)) {
            viewModel.getInfoUser()
        } else {
            Toast.makeText(this, getText(R.string.network_not_connected), Toast.LENGTH_SHORT).show()
        }
        viewModel.setNavigator(this)

        beforeActivity = intent.getStringExtra("activity")!!

        when (beforeActivity) {
            MainActivity.ACTIVITY -> {
                btnClose.visibility = View.VISIBLE
            }
            RegisterActivity.ACTIVITY -> {
                btnClose.visibility = View.INVISIBLE
            }
        }
    }

    override fun setAvatar(avatar: String) {
        if (!avatar.isBlank()) {
            Glide.with(this).load(viewModel.user.value?.avatar).centerCrop().into(imvAvatar)
            userUpdate.avatar = avatar
        }
    }

    override fun onMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun updateAvatar(avatar: Int) {
        Glide.with(this).load(resources.getIdentifier("avatar_$avatar", "drawable", packageName))
            .centerCrop().into(imvAvatar)
        userUpdate.avatar = AppConstants.AVATAR_BASE_LINK + "avatar_" + avatar + ".png"
    }

    override fun setState(email: Int, dob: Int) {
        when (email) {
            0 -> stateEmail.setImageResource(R.drawable.ic_lock)
            1 -> stateEmail.setImageResource(R.drawable.ic_friends)
            2 -> stateEmail.setImageResource(R.drawable.ic_public)
        }
        when (dob) {
            0 -> stateDob.setImageResource(R.drawable.ic_lock)
            1 -> stateDob.setImageResource(R.drawable.ic_friends)
            2 -> stateDob.setImageResource(R.drawable.ic_public)
        }
    }

    override fun setGender(gender: String) {
        if (gender.isBlank()) {
            edtGender.text = resources.getString(R.string.gender)
        } else {
            edtGender.text = gender
        }
    }

    override fun onAvatarDialog() {
        val avatarDialog  = AvatarDialog.getInstance()
        avatarDialog.avatar = userUpdate.avatar
        if (supportFragmentManager.findFragmentByTag("Avatar") == null) {
            avatarDialog.show(supportFragmentManager, "Avatar")
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
        userUpdate.gender = genderMode
        genderPopup.dismiss()
    }

    override fun onDobClick() {
        val now = Calendar.getInstance()
        val dpd: DatePickerDialog = DatePickerDialog.newInstance(this@UpdateActivity, 2000, 0, 1)
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
                userUpdate.dob_state = 0
            }
            1 -> {
                stateDob.setImageResource(R.drawable.ic_friends)
                userUpdate.dob_state = 1
            }
            2 -> {
                stateDob.setImageResource(R.drawable.ic_public)
                userUpdate.dob_state = 2
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
                stateEmail.setImageResource(R.drawable.ic_lock)
                userUpdate.email_state = 0
            }
            1 -> {
                stateEmail.setImageResource(R.drawable.ic_friends)
                userUpdate.email_state = 1
            }
            2 -> {
                stateEmail.setImageResource(R.drawable.ic_public)
                userUpdate.email_state = 2
            }
        }
        emailStatePopup.dismiss()
    }

    override fun onEdtBioFocus() {
        edtBio.requestFocus()
        showKeyboard()
    }

    override fun updateSuccess() {
        when (beforeActivity) {
            MainActivity.ACTIVITY -> {
                setResult(RESULT_CODE)
                finish()
            }
            RegisterActivity.ACTIVITY -> {
                startActivity(MainActivity.getIntent(this))
                finish()
            }
        }
    }

    override fun onSave() {
        userUpdate.full_name = edtFullName.text.toString().trim()
        userUpdate.email_address = edtEmail.text.toString().trim()
        userUpdate.bio = edtBio.text.toString().trim()
        if (NetworkUtils.isNetworkConnected(this)) {
            viewModel.updateUserInfo(userUpdate)
        }
    }

    override fun onBack() {
        finish()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val dayOfBirth = StringBuilder()
        dayOfBirth.append(dayOfMonth).append("-").append(monthOfYear + 1).append("-").append(year)
        btnDob.text = dayOfBirth
        val f = SimpleDateFormat("dd-MM-yyyy")
        try {
            val d = f.parse(dayOfBirth.toString())
            val milliseconds = d!!.time
            userUpdate.date_of_birth = milliseconds
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}
