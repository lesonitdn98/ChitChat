package me.lesonnnn.chitchat.ui.main

interface MainNavigator {
    fun openSearchView()
    fun openAddContactView()
    fun openScanView()
    fun onTabQRCodeClick()
    fun handleError(throwable: Throwable?)
}
