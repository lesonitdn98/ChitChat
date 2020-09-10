package me.lesonnnn.chitchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.login.LoginViewModel
import me.lesonnnn.chitchat.ui.main.MainViewModel
import me.lesonnnn.chitchat.ui.main.contact.ContactFragment
import me.lesonnnn.chitchat.ui.main.contact.ContactViewModel
import me.lesonnnn.chitchat.ui.main.group.GroupViewModel
import me.lesonnnn.chitchat.ui.main.home.HomeViewModel
import me.lesonnnn.chitchat.ui.main.menu.MenuViewModel
import me.lesonnnn.chitchat.ui.main.qrcode.QRViewModel
import me.lesonnnn.chitchat.ui.search.SearchViewModel
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
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(ContactViewModel::class.java) -> {
                ContactViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(GroupViewModel::class.java) -> {
                GroupViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(MenuViewModel::class.java) -> {
                MenuViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(QRViewModel::class.java) -> {
                QRViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(mDataManager, mSchedulerProvider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
