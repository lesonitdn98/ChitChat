package tech.leson.chitchat.ui.update.model

import tech.leson.chitchat.utils.AppConstants

data class UserUpdate(
    var full_name: String = "",
    var gender: String = "",
    var date_of_birth: Long = 0L,
    var dob_state: Int = 0,
    var email_address: String = "",
    var email_state: Int = 0,
    var bio: String = "",
    var avatar: String = AppConstants.AVATAR_BASE_LINK + "avatar_1.png",
)
