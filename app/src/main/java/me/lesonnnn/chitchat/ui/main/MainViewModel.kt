package me.lesonnnn.chitchat.ui.main

import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class MainViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    private var mTab = TAB.TAB_HOME

    fun onTabClick(id: Int) {
        when (id) {
            R.id.tabHome -> if (mTab != TAB.TAB_HOME) {
                navigator?.onTabSelected(TAB.TAB_HOME)
                mTab = TAB.TAB_HOME
            }
            R.id.tabContact -> if (mTab != TAB.TAB_CONTACT) {
                navigator?.onTabSelected(TAB.TAB_CONTACT)
                mTab = TAB.TAB_CONTACT
            }
            R.id.tabQrCode -> if (mTab != TAB.TAB_QR_CODE) {
                navigator?.onTabSelected(TAB.TAB_QR_CODE)
                mTab = TAB.TAB_QR_CODE
            }
            R.id.tabGroup -> if (mTab != TAB.TAB_GROUP) {
                navigator?.onTabSelected(TAB.TAB_GROUP)
                mTab = TAB.TAB_GROUP
            }
            R.id.tabMenu -> if (mTab != TAB.TAB_MENU) {
                navigator?.onTabSelected(TAB.TAB_MENU)
                mTab = TAB.TAB_MENU
            }
        }
    }

    fun onBtnAppBarClick() {
        when (mTab) {
            TAB.TAB_HOME -> navigator?.openSearchView()
            TAB.TAB_CONTACT -> navigator?.openAddContactView()
            TAB.TAB_QR_CODE -> navigator?.openScanView()
            TAB.TAB_GROUP -> TODO()
            TAB.TAB_MENU -> TODO()
        }
    }
}
