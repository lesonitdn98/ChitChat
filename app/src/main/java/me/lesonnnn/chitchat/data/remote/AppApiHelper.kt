package me.lesonnnn.chitchat.data.remote

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import me.lesonnnn.chitchat.data.model.api.request.UserRequest
import me.lesonnnn.chitchat.data.model.api.response.ServerResponse
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val userRequest: UserRequest) : ApiHelper {

    override fun register(registerData: JsonObject): Single<ServerResponse> =
        userRequest.register(registerData)

    override fun login(loginData: JsonObject): Single<ServerResponse> = userRequest.login(loginData)

    override fun getUser(token: String): Single<ServerResponse> = userRequest.getUser(token)

    override fun logout(token: String): Single<ServerResponse> = userRequest.logout(token)

}
