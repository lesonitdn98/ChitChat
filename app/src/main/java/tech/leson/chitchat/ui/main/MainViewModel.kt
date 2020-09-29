package tech.leson.chitchat.ui.main

import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class MainViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    fun onTabQRClick() {
        navigator?.onTabQRCodeClick()
    }

    fun onBtnAppBarClick() {
        when (MainActivity.tabCurrent) {
            TAB.TAB_HOME -> {}
            TAB.TAB_CONTACT -> navigator?.openSearchView()
            TAB.TAB_QR_CODE -> navigator?.openScanView()
            TAB.TAB_GROUP -> {}
            TAB.TAB_PROFILE -> navigator?.openDialogUpdate()
        }
    }
}
