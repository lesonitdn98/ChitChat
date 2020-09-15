package me.lesonnnn.chitchat.ui.main.menu

import androidx.lifecycle.MutableLiveData
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class MenuViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<MenuNavigator>(dataManager, schedulerProvider) {

    var isDarkMode: MutableLiveData<Boolean> = MutableLiveData(dataManager.isDarkMode())

    fun onClick(id: Int) {
        when (id) {
            R.id.profile -> {
            }
            R.id.friendRequests -> {
            }
            R.id.notifications -> {
            }
            R.id.signOut -> {
            }
        }
    }

    fun setDarkMode(darkMode: Boolean) {
        isDarkMode.value = darkMode
        dataManager.setDarkMode(darkMode)
    }
}
