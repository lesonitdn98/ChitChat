package me.lesonnnn.chitchat.data

import me.lesonnnn.chitchat.data.local.prefs.PreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(preferencesHelper: PreferencesHelper) :
    DataManager {

    private val mPreferencesHelper = preferencesHelper

    override fun isDarkMode(): Boolean {
        return mPreferencesHelper.isDarkMode()
    }

    override fun setDarkMode(mode: Boolean) {
        mPreferencesHelper.setDarkMode(mode)
    }

}
