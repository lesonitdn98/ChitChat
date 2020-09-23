package tech.leson.chitchat.ui.register

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import tech.leson.chitchat.R
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class RegisterViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<RegisterNavigator>(dataManager, schedulerProvider) {

    fun register(fullName: String, phoneNumber: String, password: String) {
        setIsLoading(true)
        val registerData = JsonObject()
        registerData.addProperty("full_name", fullName)
        registerData.addProperty("phone_number", phoneNumber)
        registerData.addProperty("password", password)
        compositeDisposable.add(dataManager.register(registerData)
            .doOnSuccess { response ->
                dataManager.setUserAsLogin(true)
                dataManager.updateUserInfo(
                    response.user.token,
                    response.user.id,
                    response.user.username,
                    response.user.avatar
                )
            }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ response ->
                if (response.status == 200) {
                    setIsLoading(false)
                    navigator?.onRegisterSuccess()
                } else {
                    setIsLoading(false)
                    navigator?.onRegisterFailed(response.msg)
                }
            }) { throwable ->
                setIsLoading(false)
                navigator?.onRegisterFailed(throwable.toString())
            })
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignIn -> navigator?.onBack()
            R.id.btnCreateAccount -> navigator?.onRegister()
        }
    }
}
