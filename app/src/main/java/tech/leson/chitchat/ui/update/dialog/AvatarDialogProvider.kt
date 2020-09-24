package tech.leson.chitchat.ui.update.dialog

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AvatarDialogProvider {
    @ContributesAndroidInjector
    abstract fun providerAvatarDialog(): AvatarDialog
}
