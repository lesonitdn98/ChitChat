package me.lesonnnn.chitchat.ui.main

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class MainViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    fun onTabQRClick() {
        navigator?.onTabQRCodeClick()
    }

    fun onBtnAppBarClick() {
        when (MainActivity.tabCurrent) {
            TAB.TAB_HOME -> navigator?.openSearchView()
            TAB.TAB_CONTACT -> navigator?.openAddContactView()
            TAB.TAB_QR_CODE -> navigator?.openScanView()
            TAB.TAB_GROUP -> TODO()
            TAB.TAB_MENU -> TODO()
        }
    }
}
