package tech.leson.chitchat.ui.search

import tech.leson.chitchat.model.User

interface SearchNavigator {
    fun onSearch()
    fun onSearched(data: MutableList<User>)
    fun onSearchFail(msg: String)
    fun closeSearchView()
}
