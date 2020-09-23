package tech.leson.chitchat.data.model.api.response

class ServerResponse(
    var status: Int,
    var msg: String,
    var user: UserResponse
)
