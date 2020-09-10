package me.lesonnnn.chitchat.ui.main.menu

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MenuProvider {
    @ContributesAndroidInjector
    abstract fun providerMenuFragment(): MenuFragment
}
