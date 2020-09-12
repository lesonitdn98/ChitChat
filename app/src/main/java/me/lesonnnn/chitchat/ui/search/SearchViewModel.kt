package me.lesonnnn.chitchat.ui.search

import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class SearchViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<SearchNavigator>(dataManager, schedulerProvider) {

    fun closeSearch() {
        navigator?.closeSearchView()
    }

    fun search() {

    }

}
