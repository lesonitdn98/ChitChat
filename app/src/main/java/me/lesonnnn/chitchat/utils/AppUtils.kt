package me.lesonnnn.chitchat.utils

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.View


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AppUtils {
    companion object {
        fun isEmailValid(email: String?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun delayBtnOnClick(view : View) {
            view.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                view.isEnabled = true
            },500)
        }
    }
}
