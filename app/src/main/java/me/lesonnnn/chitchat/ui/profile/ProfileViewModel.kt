package me.lesonnnn.chitchat.ui.profile

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class ProfileViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<ProfileNavigator>(dataManager, schedulerProvider) {

}
