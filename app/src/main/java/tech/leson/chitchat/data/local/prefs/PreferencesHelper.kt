package tech.leson.chitchat.data.local.prefs

interface PreferencesHelper {

    fun isDarkMode(): Boolean

    fun setDarkMode(mode: Boolean)

    fun isUserAsLogin(): Boolean

    fun setUserAsLogin(state: Boolean)

    fun getAuthToken(): String

    fun setAuthToken(token: String)

    fun getCurrentUserId(): String

    fun setCurrentUserId(id: String)

    fun getCurrentUserName(): String

    fun setCurrentUserName(username: String)

    fun getCurrentUserAvatar(): String

    fun setCurrentUserAvatar(avatar: String)
}
