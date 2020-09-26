package tech.leson.chitchat.ui.main.profile

interface ProfileNavigator {
    fun setAvatar(avatar: String)
    fun getUserFailed(msg: String)
    fun updateData()
    fun signOut()
    fun signOutFail(msg: String)
}
