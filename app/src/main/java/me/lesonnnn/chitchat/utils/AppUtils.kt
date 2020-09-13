package me.lesonnnn.chitchat.utils

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.View


class AppUtils {
    companion object {
        fun isEmailValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun delayBtnOnClick(view: View) {
            view.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                view.isEnabled = true
            }, 500)
        }
    }
}
