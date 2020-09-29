package tech.leson.chitchat.di.module

import dagger.Module
import dagger.Provides
import tech.leson.chitchat.ui.search.adapter.SearchAdapter

@Module
class SearchModule {
    @Provides
    fun provideSearchAdapter() = SearchAdapter(ArrayList())
}
