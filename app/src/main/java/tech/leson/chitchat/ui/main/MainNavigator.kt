package tech.leson.chitchat.ui.main

interface MainNavigator {
    fun getUserFailed(msg: String)
    fun openSearchView()
    fun openAddContactView()
    fun openScanView()
    fun onTabQRCodeClick()
    fun openDialogUpdate()
    fun handleError(throwable: Throwable?)
}
