package tech.leson.chitchat.data

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import tech.leson.chitchat.data.local.prefs.PreferencesHelper
import tech.leson.chitchat.data.model.api.response.ServerResponse
import tech.leson.chitchat.data.remote.ApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) :
    DataManager {

    private val mPreferencesHelper = preferencesHelper
    private val mApiHelper = apiHelper

    override fun setUserAsLoggedOut() {
        updateUserInfo("", "", "", "")
        setUserAsLogin(false)
    }

    override fun updateUserInfo(token: String, userId: String, userName: String, avatar: String) {
        setAuthToken(token)
        setCurrentUserId(userId)
        setCurrentUserName(userName)
        setCurrentUserAvatar(avatar)
    }

    override fun register(registerData: JsonObject): Single<ServerResponse> =
        mApiHelper.register(registerData)

    override fun login(loginData: JsonObject): Single<ServerResponse> = mApiHelper.login(loginData)

    override fun getUser(token: String): Single<ServerResponse> = mApiHelper.getUser(token)

    override fun update(token: String, updateData: JsonObject): Single<ServerResponse> =
        mApiHelper.update(token, updateData)

    override fun changeUsername(
        token: String,
        changeUsernameData: JsonObject,
    ): Single<ServerResponse> = mApiHelper.changeUsername(token, changeUsernameData)

    override fun changePassword(
        token: String,
        changePasswordData: JsonObject,
    ): Single<ServerResponse> = mApiHelper.changePassword(token, changePasswordData)

    override fun logout(token: String): Single<ServerResponse> = mApiHelper.logout(token)

    override fun search(token: String, searchData: JsonObject): Single<ServerResponse> =
        mApiHelper.search(token, searchData)

    override fun getUserByUsername(token: String, username: String): Single<ServerResponse> =
        mApiHelper.getUserByUsername(token, username)

    override fun isDarkMode(): Boolean = mPreferencesHelper.isDarkMode()

    override fun setDarkMode(mode: Boolean) {
        mPreferencesHelper.setDarkMode(mode)
    }

    override fun isUserAsLogin(): Boolean = mPreferencesHelper.isUserAsLogin()

    override fun setUserAsLogin(state: Boolean) {
        mPreferencesHelper.setUserAsLogin(state)
    }

    override fun getAuthToken(): String = mPreferencesHelper.getAuthToken()

    override fun setAuthToken(token: String) {
        mPreferencesHelper.setAuthToken(token)
    }

    override fun getCurrentUserId(): String = mPreferencesHelper.getCurrentUserId()

    override fun setCurrentUserId(id: String) {
        mPreferencesHelper.setCurrentUserId(id)
    }

    override fun getCurrentUserName(): String = mPreferencesHelper.getCurrentUserName()

    override fun setCurrentUserName(username: String) {
        mPreferencesHelper.setCurrentUserName(username)
    }

    override fun getCurrentUserAvatar(): String = mPreferencesHelper.getCurrentUserAvatar()

    override fun setCurrentUserAvatar(avatar: String) {
        mPreferencesHelper.setCurrentUserAvatar(avatar)
    }

}
