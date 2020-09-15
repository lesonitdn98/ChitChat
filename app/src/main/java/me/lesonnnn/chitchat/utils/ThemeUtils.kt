package me.lesonnnn.chitchat.utils

import androidx.appcompat.app.AppCompatDelegate
import me.lesonnnn.chitchat.data.local.prefs.PreferencesHelper

class ThemeUtils {

    companion object {
        fun getTheme(preferencesHelper: PreferencesHelper?) {
            if (preferencesHelper!!.isDarkMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}
