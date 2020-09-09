package me.lesonnnn.chitchat.ui.main.contact

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class ContactViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<ContactNavigator>(dataManager, schedulerProvider) {

}
