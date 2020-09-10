package me.lesonnnn.chitchat.ui.main.qrcode

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class QRViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<QRNavigator>(dataManager, schedulerProvider) {

}
