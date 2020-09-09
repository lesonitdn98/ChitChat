package me.lesonnnn.chitchat.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.lesonnnn.chitchat.ui.main.home.HomeFragment

class MainPageAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

    var mItemCount = 0

    override fun getItemCount() = mItemCount

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> HomeFragment.newInstance()
        }
        return HomeFragment.newInstance()
    }

}
