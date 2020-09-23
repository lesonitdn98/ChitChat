package tech.leson.chitchat.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import tech.leson.chitchat.di.PreferenceInfo
import javax.inject.Inject

private const val PREF_KEY_DARK_MODE = "PREF_KEY_DARK_MODE"
private const val PREF_KEY_USER_AS_LOGIN = "PREF_KEY_USER_AS_LOGIN"
private const val PREF_KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN"
private const val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
private const val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
private const val PREF_KEY_CURRENT_USER_AVATAR = "PREF_KEY_CURRENT_USER_AVATAR"

class AppPreferencesHelper @Inject constructor(
    context: Context,
    @PreferenceInfo prefFileName: String
) : PreferencesHelper {

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun isDarkMode(): Boolean = mPrefs.getBoolean(PREF_KEY_DARK_MODE, false)

    override fun setDarkMode(mode: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_DARK_MODE, mode).apply()
    }

    override fun isUserAsLogin(): Boolean = mPrefs.getBoolean(PREF_KEY_USER_AS_LOGIN, false)

    override fun setUserAsLogin(state: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_AS_LOGIN, state).apply()
    }

    override fun getAuthToken(): String = mPrefs.getString(PREF_KEY_AUTH_TOKEN, "")!!

    override fun setAuthToken(token: String) {
        mPrefs.edit().putString(PREF_KEY_AUTH_TOKEN, token).apply()
    }

    override fun getCurrentUserId(): String = mPrefs.getString(PREF_KEY_CURRENT_USER_ID, "")!!

    override fun setCurrentUserId(id: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun getCurrentUserName(): String = mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, "")!!

    override fun setCurrentUserName(username: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, username).apply()
    }

    override fun getCurrentUserAvatar(): String =
        mPrefs.getString(PREF_KEY_CURRENT_USER_AVATAR, "")!!

    override fun setCurrentUserAvatar(avatar: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_AVATAR, avatar).apply()
    }

}
