package me.lesonnnn.chitchat.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import me.lesonnnn.chitchat.BuildConfig
import me.lesonnnn.chitchat.data.AppDataManager
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.data.local.prefs.AppPreferencesHelper
import me.lesonnnn.chitchat.data.local.prefs.PreferencesHelper
import me.lesonnnn.chitchat.data.model.api.request.UserRequest
import me.lesonnnn.chitchat.data.remote.ApiHelper
import me.lesonnnn.chitchat.data.remote.AppApiHelper
import me.lesonnnn.chitchat.di.PreferenceInfo
import me.lesonnnn.chitchat.utils.AppConstants
import me.lesonnnn.chitchat.utils.rx.AppSchedulerProvider
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper =
        appPreferencesHelper

    @Provides
    @PreferenceInfo
    fun providePreferenceName() = AppConstants.PREF_NAME

    @Provides
    fun provideUserRequest(retrofit: Retrofit): UserRequest =
        retrofit.create(UserRequest::class.java)

}
