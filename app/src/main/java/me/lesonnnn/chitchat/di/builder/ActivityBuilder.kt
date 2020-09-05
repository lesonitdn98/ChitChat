package me.lesonnnn.chitchat.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.lesonnnn.chitchat.ui.main.MainActivity
import me.lesonnnn.chitchat.ui.splash.SplashActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity() : SplashActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

}
