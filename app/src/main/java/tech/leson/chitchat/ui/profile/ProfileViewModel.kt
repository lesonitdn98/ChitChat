package tech.leson.chitchat.ui.profile

import android.view.View
import androidx.lifecycle.MutableLiveData
import tech.leson.chitchat.R
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.model.Dob
import tech.leson.chitchat.model.Email
import tech.leson.chitchat.model.User
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.StringUtils
import tech.leson.chitchat.utils.rx.SchedulerProvider

class ProfileViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<ProfileNavigator>(dataManager, schedulerProvider) {

    val user: MutableLiveData<User> = MutableLiveData()
    val isEmail: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDob: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPhone: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getUserInfo(username: String) {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.getUserByUsername(dataManager.getAuthToken(), username)
                .doOnSuccess {
                    user.postValue(User(
                        null,
                        it.user.full_name,
                        null,
                        it.user.username,
                        Email(null, it.user.email.email_address, null),
                        it.user.avatar,
                        it.user.gender,
                        Dob(null,
                            if (it.user.dob.date_of_birth != 0L) StringUtils.getDate(it.user.dob.date_of_birth) else "",
                            null),
                        it.user.bio,
                        null
                    ))
                    if (it.user.email.email_address != "") isEmail.postValue(true)
                    if (it.user.dob.date_of_birth != 0L) isDob.postValue(true)
                    if (it.user.phone.phone_number != "") isPhone.postValue(true)
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    navigator?.setAvatar(it.user.avatar)
                    setIsLoading(false)
                }) { throwable ->
                    setIsLoading(false)
                    navigator?.getUserFailed(throwable.toString())
                })
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnClose -> navigator?.onBack()
            R.id.btnAddContact -> navigator?.addContact()
        }
    }
}
