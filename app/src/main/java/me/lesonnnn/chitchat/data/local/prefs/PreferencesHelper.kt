package me.lesonnnn.chitchat.data.local.prefs

interface PreferencesHelper {

    fun getDarkMode() : Boolean

    fun setDarkMode(mode: Boolean)
}