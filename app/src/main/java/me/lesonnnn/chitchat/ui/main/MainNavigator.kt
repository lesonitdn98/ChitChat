package me.lesonnnn.chitchat.ui.main

interface MainNavigator {
    fun onTabSelected(tab: TAB)
    fun openSearchView()
    fun handleError(throwable: Throwable?)
}
