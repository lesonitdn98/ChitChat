package tech.leson.chitchat.data.model.api.request

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import tech.leson.chitchat.data.model.api.response.ServerResponse

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
    @PUT("/api/user/update")
    fun update(
        @Header("auth-token") token: String,
        @Body updateData: JsonObject,
    ): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @PUT("/api/user/username")
    fun changeUsername(
        @Header("auth-token") token: String,
        @Body changeUsernameData: JsonObject,
    ): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @PUT("/api/user/password")
    fun changePassword(
        @Header("auth-token") token: String,
        @Body changePasswordData: JsonObject,
    ): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/logout")
    fun logout(@Header("auth-token") token: String): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @POST("api/user/search")
    fun search(
        @Header("auth-token") token: String,
        @Body searchData: JsonObject,
    ): Single<ServerResponse>

    @Headers("Content-Type: application/json")
    @GET("api/user/{username}")
    fun getUserByUsername(
        @Header("auth-token") token: String,
        @Path("username") username: String,
    ): Single<ServerResponse>
}
