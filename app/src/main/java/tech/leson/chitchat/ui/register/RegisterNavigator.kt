package tech.leson.chitchat.ui.register

interface RegisterNavigator {
    fun onRegisterSuccess()
    fun onRegisterFailed(msg: String)
    fun onRegister()
    fun onBack()
}
