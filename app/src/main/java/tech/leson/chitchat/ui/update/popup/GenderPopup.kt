package tech.leson.chitchat.ui.update.popup

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.layout_popup_gender.view.*
import tech.leson.chitchat.R
import tech.leson.chitchat.ui.update.UpdateActivity
import tech.leson.chitchat.utils.AppConstants
import javax.inject.Inject

class GenderPopup @Inject constructor(private var activity: UpdateActivity) :
    PopupWindow(activity), View.OnClickListener {

    @SuppressLint("UseCompatLoadingForDrawables", "InflateParams")
    private fun init() {
        val view = LayoutInflater.from(activity).inflate(R.layout.layout_popup_gender, null)
        setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.bg_popup))
        view.btnMale.setOnClickListener(this)
        view.btnFemale.setOnClickListener(this)
        view.btnUnknown.setOnClickListener(this)
        contentView = view
    }

    init {
        init()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnMale -> activity.onGenderSelected(AppConstants.MALE)
            R.id.btnFemale -> activity.onGenderSelected(AppConstants.FEMALE)
            R.id.btnUnknown -> activity.onGenderSelected(AppConstants.UNKNOWN)
        }
    }
}
