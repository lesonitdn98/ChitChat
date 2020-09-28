package tech.leson.chitchat.ui.username

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import tech.leson.chitchat.R
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class UsernameViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<UsernameNavigator>(dataManager, schedulerProvider) {

    val username = MutableLiveData(dataManager.getCurrentUserName())

    fun changeUsername(newUsername: String) {
        setIsLoading(true)
        if (newUsername != username.value) {
            val changeUsernameData = JsonObject()
            changeUsernameData.addProperty("username", newUsername)
            compositeDisposable.add(dataManager.changeUsername(dataManager.getAuthToken(),
                changeUsernameData).doOnSuccess {
                dataManager.setCurrentUserName(it.user.username)
                username.postValue(it.user.username)
            }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    navigator?.changeSuccess()
                }) { throwable ->
                    setIsLoading(false)
                    navigator?.changeFailed(throwable.toString())
                })
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnClose -> navigator?.onBack()
            R.id.btnSave -> navigator?.onSave()
        }
    }

}
