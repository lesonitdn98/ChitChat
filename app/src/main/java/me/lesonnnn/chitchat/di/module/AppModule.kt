package me.lesonnnn.chitchat.di.module

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.lesonnnn.chitchat.data.AppDataManager
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.data.local.prefs.AppPreferencesHelper
import me.lesonnnn.chitchat.data.local.prefs.PreferencesHelper
import me.lesonnnn.chitchat.di.PreferenceInfo
import me.lesonnnn.chitchat.utils.AppConstants
import me.lesonnnn.chitchat.utils.rx.AppSchedulerProvider
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper =
        appPreferencesHelper

    @Provides
    @PreferenceInfo
    fun providePreferenceName() = AppConstants.PREF_NAME

}
