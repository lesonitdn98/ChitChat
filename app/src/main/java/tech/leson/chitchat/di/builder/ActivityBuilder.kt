package tech.leson.chitchat.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.leson.chitchat.di.module.MainModule
import tech.leson.chitchat.ui.login.LoginActivity
import tech.leson.chitchat.ui.main.MainActivity
import tech.leson.chitchat.ui.main.contact.ContactProvider
import tech.leson.chitchat.ui.main.dialog.UpdateDialogProvider
import tech.leson.chitchat.ui.main.group.GroupProvider
import tech.leson.chitchat.ui.main.home.HomeProvider
import tech.leson.chitchat.ui.main.profile.ProfileProvider
import tech.leson.chitchat.ui.main.qrcode.QRProvider
import tech.leson.chitchat.ui.register.RegisterActivity
import tech.leson.chitchat.ui.search.SearchActivity
import tech.leson.chitchat.ui.splash.SplashActivity
import tech.leson.chitchat.ui.update.UpdateActivity
import tech.leson.chitchat.ui.update.dialog.AvatarDialogProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector(
        modules = [MainModule::class,
            HomeProvider::class,
            ContactProvider::class,
            GroupProvider::class,
            ProfileProvider::class,
            QRProvider::class,
            UpdateDialogProvider::class,
        ]
    )
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector(
        modules = [AvatarDialogProvider::class]
    )
    abstract fun bindUpdateActivity(): UpdateActivity

}
