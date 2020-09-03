package me.lesonnnn.chitchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.main.MainViewModel
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
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mDataManager, mSchedulerProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
