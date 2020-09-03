package me.lesonnnn.chitchat

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import me.lesonnnn.chitchat.di.component.DaggerAppComponent
import javax.inject.Inject

class ChitChatApp : Application(), HasAndroidInjector {

    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    override fun androidInjector(): AndroidInjector<Any?>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}
