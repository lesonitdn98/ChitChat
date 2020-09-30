package tech.leson.chitchat.ui.main.profile

import androidx.lifecycle.MutableLiveData
import tech.leson.chitchat.R
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.model.Dob
import tech.leson.chitchat.model.Email
import tech.leson.chitchat.model.Phone
import tech.leson.chitchat.model.User
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.StringUtils
import tech.leson.chitchat.utils.rx.SchedulerProvider

class MyProfileViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<ProfileNavigator>(dataManager, schedulerProvider) {

    var isDarkMode: MutableLiveData<Boolean> = MutableLiveData(dataManager.isDarkMode())
    val user: MutableLiveData<User> = MutableLiveData()

    fun getUserInfo() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.getUser(dataManager.getAuthToken())
                .doOnSuccess { response ->
                    user.postValue(
                        User(
                            response.user.id,
                            response.user.full_name,
                            Phone(
                                response.user.phone.phone_id,
                                response.user.phone.phone_number,
                                response.user.phone.phone_state
                            ),
                            response.user.username,
                            Email(
                                response.user.email.email_id,
                                response.user.email.email_address,
                                response.user.email.email_state
                            ),
                            response.user.avatar,
                            response.user.gender,
                            Dob(
                                response.user.dob.dob_id,
                                StringUtils.getDate(response.user.dob.date_of_birth),
                                response.user.dob.dob_state
                            ),
                            response.user.bio,
                            response.user.state
                        )
                    )
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    navigator?.setAvatar(it.user.avatar)
                }) { throwable ->
                    setIsLoading(false)
                    navigator?.getUserFailed(throwable.toString())
                })
    }

    fun setDarkMode(darkMode: Boolean) {
        isDarkMode.value = darkMode
        dataManager.setDarkMode(darkMode)
    }

    fun onClick(id: Int) {
        when (id) {
            R.id.friendRequests -> {
            }
            R.id.notifications -> {
            }
            R.id.signOut -> {
                compositeDisposable.add(
                    dataManager.logout(dataManager.getAuthToken())
                        .doOnSuccess {
                            dataManager.setUserAsLoggedOut()
                            navigator?.signOut()
                        }
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({}) { throwable ->
                            navigator?.signOutFail(throwable.toString())
                        })
            }
        }
    }

}
