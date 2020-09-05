package me.lesonnnn.chitchat.ui.main

import androidx.lifecycle.MutableLiveData
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class MainViewModel<N> constructor(dataManager: DataManager,
                    schedulerProvider: SchedulerProvider
) : BaseViewModel<N>(dataManager, schedulerProvider) {

    private var text: MutableLiveData<String> = MutableLiveData()

    init {
        text.value = "ChitChat, Hello Word!!!"
    }

    fun getText() : MutableLiveData<String> {
        return text
    }

    fun setText(string : String) {
        text.value = string
    }
}
