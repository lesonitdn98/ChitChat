package me.lesonnnn.chitchat.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.lesonnnn.chitchat.di.module.MainModule
import me.lesonnnn.chitchat.ui.login.LoginActivity
import me.lesonnnn.chitchat.ui.main.MainActivity
import me.lesonnnn.chitchat.ui.main.contact.ContactProvider
import me.lesonnnn.chitchat.ui.main.group.GroupProvider
import me.lesonnnn.chitchat.ui.main.home.HomeProvider
import me.lesonnnn.chitchat.ui.main.menu.MenuProvider
import me.lesonnnn.chitchat.ui.main.qrcode.QRProvider
import me.lesonnnn.chitchat.ui.register.RegisterActivity
import me.lesonnnn.chitchat.ui.search.SearchActivity
import me.lesonnnn.chitchat.ui.splash.SplashActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindRegisterActivity() : RegisterActivity

    @ContributesAndroidInjector(
        modules = [MainModule::class,
            HomeProvider::class,
            ContactProvider::class,
            GroupProvider::class,
            MenuProvider::class,
            QRProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSearchActivity() : SearchActivity

}
