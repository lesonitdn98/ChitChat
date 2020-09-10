package me.lesonnnn.chitchat.utils

import android.util.Patterns


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AppUtils {
    companion object {
        fun isEmailValid(email: String?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}
