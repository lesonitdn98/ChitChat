package me.lesonnnn.chitchat.ui.main.group

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GroupProvider {

    @ContributesAndroidInjector
    abstract fun providerGroupFragment(): GroupFragment

}
