package me.lesonnnn.chitchat.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.lesonnnn.chitchat.ui.main.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

}
