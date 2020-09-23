package tech.leson.chitchat.ui.login

interface LoginNavigator {
    fun login()
    fun onLoginSuccess()
    fun onLoginFailed(mess: String)
    fun onCreateAccount()
}
