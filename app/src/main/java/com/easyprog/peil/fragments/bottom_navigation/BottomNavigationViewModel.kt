package com.easyprog.peil.fragments.bottom_navigation

import androidx.lifecycle.SavedStateHandle
import com.easyprog.core.views.BaseViewModel
import com.easyprog.peil.fragments.community.CommunityFragment
import com.easyprog.peil.fragments.lessons_list.LessonsListFragment
import com.easyprog.peil.fragments.repeat_word.RepeatWordFragment
import com.easyprog.core.navigator.Navigator

class BottomNavigationViewModel constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle,
    screen: BottomNavigationFragment.Screen
) : BaseViewModel() {

    fun onLessonItemPressed() {
        navigator.launch(LessonsListFragment.Screen())
    }

    fun onRepeatWordItemPressed() {
        navigator.launch(RepeatWordFragment.Screen())
    }

    fun onCommunityItemPressed() {
        navigator.launch(CommunityFragment.Screen())
    }

}