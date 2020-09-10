package me.lesonnnn.chitchat.ui.main

interface MainNavigator {
    fun onTabSelected(tab: TAB)
    fun openSearchView()
    fun openAddContactView()
    fun openScanView()
    fun handleError(throwable: Throwable?)
}
