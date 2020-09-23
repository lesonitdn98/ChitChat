package tech.leson.chitchat.ui.search

import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class SearchViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<SearchNavigator>(dataManager, schedulerProvider) {

    fun closeSearch() {
        navigator?.closeSearchView()
    }

    fun search() {

    }

}
