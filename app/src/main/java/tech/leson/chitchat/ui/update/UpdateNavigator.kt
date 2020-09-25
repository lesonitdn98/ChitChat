package tech.leson.chitchat.ui.update

interface UpdateNavigator {
    fun getAvatar(avatar: String)
    fun getUserFailed(msg: String)
    fun updateAvatar(avatar: Int)
    fun onAvatarDialog()
    fun onPopupGender()
    fun onGenderSelected(genderMode: String)
    fun onDobClick()
    fun onPopupDobState()
    fun onDobStateSelected(state: Int)
    fun onPopupEmailState()
    fun onEmailStateSelected(state: Int)
    fun onEdtBioFocus()
    fun onSave()
    fun onBack()
}
