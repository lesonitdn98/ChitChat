package me.lesonnnn.chitchat.ui.main.home

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class HomeViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<HomeNavigator>(
    dataManager,
    schedulerProvider
) {

}