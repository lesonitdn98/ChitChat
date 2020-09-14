package me.lesonnnn.chitchat.ui.main.menu

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class MenuViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<MenuNavigator>(dataManager, schedulerProvider) {

    fun onClick(id : Int) {

    }

}
