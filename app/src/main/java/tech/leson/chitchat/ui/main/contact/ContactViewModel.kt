package tech.leson.chitchat.ui.main.contact

import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class ContactViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<ContactNavigator>(dataManager, schedulerProvider) {

}
