package tech.leson.chitchat.ui.main.dialog

import android.view.View
import tech.leson.chitchat.R
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.ui.base.BaseViewModel
import tech.leson.chitchat.utils.rx.SchedulerProvider

class UpdateDialogViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<UpdateDialogNavigator>(dataManager, schedulerProvider) {

    fun onClick(view: View) {
        when (view.id) {
            R.id.updateInformation -> navigator?.openUpdateInformation()
            R.id.changeUsername -> navigator?.openChangeUsername()
            R.id.changePassword -> navigator?.openChangePassword()
        }
    }
}
