package me.lesonnnn.chitchat.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import me.lesonnnn.chitchat.di.PreferenceInfo
import javax.inject.Inject

private const val PREF_KEY_DARK_MODE = "PREF_KEY_DARK_MODE"

class AppPreferencesHelper @Inject constructor(context: Context, @PreferenceInfo prefFileName : String) : PreferencesHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getDarkMode(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_DARK_MODE, false)
    }

    override fun setDarkMode(mode: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_DARK_MODE, mode).apply()
    }

}