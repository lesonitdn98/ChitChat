package me.lesonnnn.chitchat.ui.main.group

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class GroupViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<GroupNavigator>(dataManager, schedulerProvider) {

}
