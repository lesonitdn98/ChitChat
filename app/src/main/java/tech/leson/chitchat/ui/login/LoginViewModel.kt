package tech.leson.chitchat.ui.login

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class LoginViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<LoginNavigator>(dataManager, schedulerProvider) {

    var phone: MutableLiveData<String> = MutableLiveData("0394188885")
    var password: MutableLiveData<String> = MutableLiveData("123123")

    fun login(phone: String, password: String) {
        setIsLoading(true)
        this.phone.value = phone
        this.password.value = password
        val loginData = JsonObject()
        loginData.addProperty("phone_number", phone)
        loginData.addProperty("password", password)
        compositeDisposable.add(dataManager
            .login(loginData)
            .doOnSuccess { response ->
                dataManager
                    .updateUserInfo(
                        response.user.token,
                        response.user.id,
                        response.user.username,
                        response.user.avatar
                    )
                dataManager.setUserAsLogin(true)
            }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ response ->
                if (response.status == 200) {
                    setIsLoading(false)
                    navigator?.onLoginSuccess()
                } else {
                    setIsLoading(false)
                    navigator?.onLoginFailed(response.msg)
                }
            }) { throwable ->
                setIsLoading(false)
                navigator?.onLoginFailed(throwable.toString())
            })
    }

    fun loginCommit() {
        navigator?.login()
    }

    fun isEmailAndPasswordValid(phone: String, password: String): Boolean {
        if (phone.isBlank() && phone.length < 10) {
            return false
        } else if (password.isBlank()) {
            return false
        }
        return true
    }

    fun onCreateAccountClick() {
        navigator?.onCreateAccount()
    }
}
