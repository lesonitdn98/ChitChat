package tech.leson.chitchat.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import tech.leson.chitchat.BuildConfig
import tech.leson.chitchat.data.AppDataManager
import tech.leson.chitchat.data.DataManager
import tech.leson.chitchat.data.local.prefs.AppPreferencesHelper
import tech.leson.chitchat.data.local.prefs.PreferencesHelper
import tech.leson.chitchat.data.model.api.request.UserRequest
import tech.leson.chitchat.data.remote.ApiHelper
import tech.leson.chitchat.data.remote.AppApiHelper
import tech.leson.chitchat.di.PreferenceInfo
import tech.leson.chitchat.utils.AppConstants
import tech.leson.chitchat.utils.rx.AppSchedulerProvider
import tech.leson.chitchat.utils.rx.SchedulerProvider
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
