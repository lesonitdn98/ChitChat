package me.lesonnnn.chitchat.di.module

import dagger.Module
import dagger.Provides
import me.lesonnnn.chitchat.ui.main.MainActivity
import me.lesonnnn.chitchat.ui.main.MainPageAdapter

@Module
class MainModule {

    @Provides
    fun provideMainPagerAdapter(activity: MainActivity) =
        MainPageAdapter(activity)

}
