package tech.leson.chitchat.ui.main.profile

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileProvider {

    @ContributesAndroidInjector
    abstract fun providerProfileFragment(): ProfileFragment

}
