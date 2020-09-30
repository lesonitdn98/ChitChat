package tech.leson.chitchat.ui.profile

interface ProfileNavigator {
    fun getUserFailed(msg: String)
    fun setAvatar(avatar: String)
    fun addContact()
    fun onBack()
}
