package tech.leson.chitchat.ui.update

interface UpdateNavigator {
    fun setAvatar(avatar: String)
    fun onMessage(msg: String)
    fun updateAvatar(avatar: Int)
    fun setState(email: Int, dob: Int)
    fun setGender(gender: String)
    fun onAvatarDialog()
    fun onPopupGender()
    fun onGenderSelected(genderMode: String)
    fun onDobClick()
    fun onPopupDobState()
    fun onDobStateSelected(state: Int)
    fun onPopupEmailState()
    fun onEmailStateSelected(state: Int)
    fun onEdtBioFocus()
    fun updateSuccess()
    fun onSave()
    fun onBack()
}
