package me.lesonnnn.chitchat.ui.main

import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class MainViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    fun onTabClick(id: Int) {
        when (id) {
            R.id.tabHome -> navigator?.onTabSelected(TAB.TAB_HOME)
            R.id.tabContact -> navigator?.onTabSelected(TAB.TAB_CONTACT)
            R.id.tabQrCode -> navigator?.onTabSelected(TAB.TAB_QR_CODE)
            R.id.tabGroup -> navigator?.onTabSelected(TAB.TAB_GROUP)
            R.id.tabMenu -> navigator?.onTabSelected(TAB.TAB_MENU)
        }
    }
}
