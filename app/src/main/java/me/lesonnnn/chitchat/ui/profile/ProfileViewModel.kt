package me.lesonnnn.chitchat.ui.profile

import androidx.lifecycle.MutableLiveData
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.data.model.api.response.UserResponse
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class ProfileViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<ProfileNavigator>(dataManager, schedulerProvider) {

    val user: MutableLiveData<UserResponse> = MutableLiveData()

    fun getUserInfo() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.getUser(dataManager.getAuthToken())
                .doOnSuccess { response -> user.postValue(response.user) }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    setIsLoading(false)
                }) { throwable ->
                    setIsLoading(false)
                    navigator?.getUserFailed(throwable.toString())
                })
    }

    fun onClickBack() {
        navigator?.onBack()
    }

    fun onClickEdit() {}

}
