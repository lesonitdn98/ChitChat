package tech.leson.chitchat.di.module

import dagger.Module
import dagger.Provides
import tech.leson.chitchat.ui.main.MainActivity
import tech.leson.chitchat.ui.main.MainTabAdapter
import javax.inject.Singleton

@Module
class MainModule {
    @Provides
    fun providesViewHolder(mainActivity: MainActivity): MainTabAdapter = MainTabAdapter(mainActivity)
}
