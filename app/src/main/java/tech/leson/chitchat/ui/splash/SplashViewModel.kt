package tech.leson.chitchat.ui.splash

import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

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
