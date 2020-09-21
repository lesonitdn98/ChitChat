package me.lesonnnn.chitchat.data

import me.lesonnnn.chitchat.data.local.prefs.PreferencesHelper
import me.lesonnnn.chitchat.data.remote.ApiHelper

interface DataManager : ApiHelper, PreferencesHelper {

    fun setUserAsLoggedOut()

    fun updateUserInfo(
        token: String,
        userId: String,
        userName: String,
        avatar: String
    )

}
