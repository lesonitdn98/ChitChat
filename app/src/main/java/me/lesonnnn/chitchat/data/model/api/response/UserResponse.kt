package me.lesonnnn.chitchat.data.model.api.response

class UserResponse(
    val id: String,
    val token: String,
    val phone: PhoneResponse,
    val full_name: String,
    val username: String,
    val email: EmailResponse,
    val dob: DobResponse,
    val gender: String,
    val bio: String,
    val avatar: String,
    val state: Int,
    val created_at: Long
)
