package tech.leson.chitchat.ui.main.profile

interface ProfileNavigator {
    fun getUserFailed(msg: String)
    fun signOut()
    fun signOutFail(msg: String)
}
