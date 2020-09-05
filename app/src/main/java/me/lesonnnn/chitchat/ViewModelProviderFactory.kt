package me.lesonnnn.chitchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.main.MainNavigator
import me.lesonnnn.chitchat.ui.main.MainViewModel
import me.lesonnnn.chitchat.ui.splash.SplashActivity
import me.lesonnnn.chitchat.ui.splash.SplashNavigator
import me.lesonnnn.chitchat.ui.splash.SplashViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelProviderFactory @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {

    private val mDataManager: DataManager = dataManager
    private val mSchedulerProvider: SchedulerProvider = schedulerProvider

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel<SplashNavigator>(mDataManager, mSchedulerProvider) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel<MainNavigator>(mDataManager, mSchedulerProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
