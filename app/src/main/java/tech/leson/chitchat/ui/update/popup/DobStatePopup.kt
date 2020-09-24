package tech.leson.chitchat.ui.update.popup

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.layout_popup_state.view.*
import tech.leson.chitchat.R
import tech.leson.chitchat.ui.update.UpdateActivity
import javax.inject.Inject

class DobStatePopup @Inject constructor(private var activity: UpdateActivity) :
    PopupWindow(activity), View.OnClickListener {

    @SuppressLint("UseCompatLoadingForDrawables", "InflateParams")
    private fun init() {
        val view = LayoutInflater.from(activity).inflate(R.layout.layout_popup_state, null)
        setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.bg_popup))
        view.btnPublic.setOnClickListener(this)
        view.btnFriends.setOnClickListener(this)
        view.btnOnlyMe.setOnClickListener(this)
        contentView = view
    }

    init {
        init()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnPublic -> activity.onDobStateSelected(2)
            R.id.btnFriends -> activity.onDobStateSelected(1)
            R.id.btnOnlyMe -> activity.onDobStateSelected(0)
        }
    }
}
