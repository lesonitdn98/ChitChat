package tech.leson.chitchat.ui.main.qrcode

import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class QRViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<QRNavigator>(dataManager, schedulerProvider) {

}
