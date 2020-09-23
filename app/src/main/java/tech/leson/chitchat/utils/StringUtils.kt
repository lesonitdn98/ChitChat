package tech.leson.chitchat.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class StringUtils {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getDate(time: Long): String {
            val formatter = SimpleDateFormat("dd-MM-yyyy")

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            return formatter.format(calendar.time)
        }
    }
}
