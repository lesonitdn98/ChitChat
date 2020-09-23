package tech.leson.chitchat.data.remote

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import tech.leson.chitchat.data.model.api.response.ServerResponse

interface ApiHelper {

    fun register(registerData: JsonObject): Single<ServerResponse>

    fun login(loginData: JsonObject): Single<ServerResponse>

    fun getUser(token: String): Single<ServerResponse>

    fun update(token: String, updateData: JsonObject): Single<ServerResponse>

    fun changePassword(token: String, changePasswordData: JsonObject): Single<ServerResponse>

    fun logout(token: String): Single<ServerResponse>

}
