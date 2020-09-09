package me.lesonnnn.chitchat.ui.main.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeProvider {
    @ContributesAndroidInjector
    abstract fun providerHomeFragment(): HomeFragment
}
