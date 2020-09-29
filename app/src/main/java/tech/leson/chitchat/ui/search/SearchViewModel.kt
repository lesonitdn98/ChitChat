package tech.leson.chitchat.ui.search

import android.view.View
import com.google.gson.JsonObject
import tech.leson.chitchat.R
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.model.User
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class SearchViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<SearchNavigator>(dataManager, schedulerProvider) {

    fun search(username: String) {
        setIsLoading(true)
        val searchData = JsonObject()
        searchData.addProperty("username", username)
        compositeDisposable.add(dataManager.search(dataManager.getAuthToken(), searchData)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                val users = ArrayList<User>()
                for (userResponse in it.users) {
                    users.add(User(
                        null,
                        userResponse.full_name,
                        null,
                        userResponse.username,
                        null,
                        userResponse.avatar,
                        null,
                        null,
                        null,
                        null
                    ))
                }
                navigator?.onSearched(users)
                setIsLoading(false)
            }) {
                setIsLoading(false)
                navigator?.onSearchFail(it.toString())
            }
        )
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnClose -> navigator?.closeSearchView()
            R.id.btnSearch -> navigator?.onSearch()
        }
    }

}
