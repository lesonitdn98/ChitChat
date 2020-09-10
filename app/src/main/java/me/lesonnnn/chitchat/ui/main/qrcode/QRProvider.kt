package me.lesonnnn.chitchat.ui.main.qrcode

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QRProvider {
    @ContributesAndroidInjector
    abstract fun providerQRFragment(): QRFragment
}
