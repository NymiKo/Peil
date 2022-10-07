package com.easyprog.peil.fragments.bottom_navigation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.easyprog.core.navigator.Navigator
import com.easyprog.peil.fragments.community.CommunityFragment
import com.easyprog.peil.fragments.lessons_list.LessonsListFragment
import com.easyprog.peil.fragments.repeat_word.RepeatWordFragment

class BottomNavigationViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LessonsListFragment.setScreen()
            1 -> RepeatWordFragment()
            2 -> CommunityFragment()
            else -> LessonsListFragment()
        }
    }
}