package tech.leson.chitchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.login.LoginViewModel
import tech.leson.chitchat.ui.main.MainViewModel
import tech.leson.chitchat.ui.main.contact.ContactViewModel
import tech.leson.chitchat.ui.main.group.GroupViewModel
import tech.leson.chitchat.ui.main.home.HomeViewModel
import tech.leson.chitchat.ui.main.profile.ProfileViewModel
import tech.leson.chitchat.ui.main.qrcode.QRViewModel
import tech.leson.chitchat.ui.register.RegisterViewModel
import tech.leson.chitchat.ui.search.SearchViewModel
import tech.leson.chitchat.ui.splash.SplashViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider
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
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(mDataManager, mSchedulerProvider) as T
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
            modelClass.isAssignableFrom(QRViewModel::class.java) -> {
                QRViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(mDataManager, mSchedulerProvider) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(mDataManager, mSchedulerProvider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
