package me.lesonnnn.chitchat.ui.register

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class RegisterViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<RegisterNavigator>(dataManager, schedulerProvider) {

    fun onClickBack() {
        navigator?.onBack()
    }
}
