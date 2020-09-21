package me.lesonnnn.chitchat.data.model.api.request

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import me.lesonnnn.chitchat.data.model.api.response.ServerResponse
import retrofit2.http.*

interface UserRequest {

    @Headers("Content-Type: application/json")
    @POST("/api/user")
    fun register(@Body registerData: JsonObject): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/login")
    fun login(@Body loginData: JsonObject): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/user")
    fun getUser(@Header("auth-token") token: String): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/user/logout")
    fun logout(@Header("auth-token") token: String): Single<ServerResponse>

}
