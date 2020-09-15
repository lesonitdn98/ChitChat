package me.lesonnnn.chitchat.data.local.prefs

interface PreferencesHelper {

    fun isDarkMode() : Boolean

    fun setDarkMode(mode: Boolean)
}