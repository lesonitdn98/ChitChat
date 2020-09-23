package tech.leson.chitchat.ui.profile

import androidx.lifecycle.MutableLiveData
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.data.model.api.response.UserResponse
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

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
                .subscribe({
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
