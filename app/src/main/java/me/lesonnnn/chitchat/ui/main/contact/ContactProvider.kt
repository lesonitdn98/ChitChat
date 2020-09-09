package me.lesonnnn.chitchat.ui.main.contact

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContactProvider {
    @ContributesAndroidInjector
    abstract fun providerContactFragment(): ContactFragment
}
