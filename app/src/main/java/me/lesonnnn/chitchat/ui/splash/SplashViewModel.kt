package me.lesonnnn.chitchat.ui.splash

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class SplashViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

    fun checkUserAsLogin() {
        if (dataManager.isUserAsLogin()) {
            navigator?.openMainActivity()
        } else {
            navigator?.openLoginActivity()
        }
    }
}
