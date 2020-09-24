package tech.leson.chitchat.ui.update.dialog

import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class AvatarDialogViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<AvatarDialogNavigator>(dataManager, schedulerProvider) {

}
