package me.lesonnnn.chitchat.di.module

import dagger.Module
import dagger.Provides
import me.lesonnnn.chitchat.ui.main.MainActivity
import me.lesonnnn.chitchat.ui.main.MainTabAdapter
import javax.inject.Singleton

@Module
class MainModule {
    @Provides
    fun providesViewHolder(mainActivity: MainActivity): MainTabAdapter = MainTabAdapter(mainActivity)
}
