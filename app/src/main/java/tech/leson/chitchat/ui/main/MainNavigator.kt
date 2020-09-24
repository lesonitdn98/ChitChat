package tech.leson.chitchat.ui.main

interface MainNavigator {
    fun openSearchView()
    fun openAddContactView()
    fun openScanView()
    fun onTabQRCodeClick()
    fun openDialogUpdate()
    fun handleError(throwable: Throwable?)
}
