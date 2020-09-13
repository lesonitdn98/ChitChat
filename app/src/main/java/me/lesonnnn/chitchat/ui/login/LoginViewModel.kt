package me.lesonnnn.chitchat.ui.login

import androidx.lifecycle.MutableLiveData
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.ui.base.BaseViewModel
import me.lesonnnn.chitchat.utils.AppUtils
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider

class LoginViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<LoginNavigator>(dataManager, schedulerProvider) {

    var email: MutableLiveData<String> = MutableLiveData("leson@gmail.com")
    var password: MutableLiveData<String> = MutableLiveData("123123")

    fun login(email: String, password: String) {
        this.email.value = email
        this.password.value = password
        navigator?.onLoginSuccess()
    }

    fun loginCommit() {
        navigator?.login()
    }

    fun isEmailAndPasswordValid(email: String, password: String?): Boolean {
        if (email.isBlank() || !AppUtils.isEmailValid(email)) {
            return false
        } else if (password == null || password.isBlank()) {
            return false
        }
        return true
    }

    fun onCreateAccountClick() {
        navigator?.onCreateAccount()
    }
}
