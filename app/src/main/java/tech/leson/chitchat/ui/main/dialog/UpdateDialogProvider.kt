package tech.leson.chitchat.ui.main.dialog

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UpdateDialogProvider {
    @ContributesAndroidInjector
    abstract fun providerUpdateDialog(): UpdateDialog
}
