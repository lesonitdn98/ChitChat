package me.lesonnnn.chitchat.ui.main.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentProvider {
    @ContributesAndroidInjector
    abstract fun providerHomeFragment(): HomeFragment
}
