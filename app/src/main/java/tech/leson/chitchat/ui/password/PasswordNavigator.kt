package tech.leson.chitchat.ui.password

interface PasswordNavigator {
    fun onSave()
    fun onChangePassSuccess()
    fun onChangePassFailed(msg: String)
    fun onBack()
}
