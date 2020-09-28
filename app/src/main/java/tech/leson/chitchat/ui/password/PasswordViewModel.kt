package tech.leson.chitchat.ui.password

import android.view.View
import com.google.gson.JsonObject
import tech.leson.chitchat.R
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class PasswordViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<PasswordNavigator>(dataManager, schedulerProvider) {

    fun changePassword(oldPass: String, newPass: String) {
        setIsLoading(true)
        val changePassData = JsonObject()
        changePassData.addProperty("password", oldPass)
        changePassData.addProperty("new_password", newPass)

        compositeDisposable.add(dataManager.changePassword(dataManager.getAuthToken(),
            changePassData)
            .doOnSuccess {
                dataManager
                    .updateUserInfo(
                        it.user.token,
                        it.user.id,
                        it.user.username,
                        it.user.avatar
                    )
                dataManager.setUserAsLogin(true)
            }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ response ->
                if (response.status == 200) {
                    setIsLoading(false)
                    navigator?.onChangePassSuccess()
                } else {
                    setIsLoading(false)
                    navigator?.onChangePassFailed(response.msg)
                }
            }) { throwable ->
                setIsLoading(false)
                navigator?.onChangePassFailed(throwable.toString())
            })
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnClose -> navigator?.onBack()
            R.id.btnSave -> navigator?.onSave()
        }
    }
}
