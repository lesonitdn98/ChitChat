package tech.leson.chitchat.ui.main.group

import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class GroupViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<GroupNavigator>(dataManager, schedulerProvider) {

}
