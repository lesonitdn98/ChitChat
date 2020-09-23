package tech.leson.chitchat.data

import tech.leson.chitchat.data.local.prefs.PreferencesHelper
import tech.leson.chitchat.data.remote.ApiHelper

interface DataManager : ApiHelper, PreferencesHelper {

    fun setUserAsLoggedOut()

    fun updateUserInfo(
        token: String,
        userId: String,
        userName: String,
        avatar: String
    )

}
