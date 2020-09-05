package me.lesonnnn.chitchat.ui.splash

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class SplashViewModel<N> constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<N>(dataManager, schedulerProvider) {

}