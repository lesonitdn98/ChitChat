package tech.leson.chitchat.ui.profile

interface ProfileNavigator {
    fun onBack()
    fun getUserFailed(msg: String)
}
