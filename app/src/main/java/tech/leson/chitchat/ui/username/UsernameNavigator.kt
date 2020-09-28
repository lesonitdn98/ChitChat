package tech.leson.chitchat.ui.username

interface UsernameNavigator {
    fun onSave()
    fun changeSuccess()
    fun changeFailed(msg: String)
    fun onBack()
}