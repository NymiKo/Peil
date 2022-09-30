package com.easyprog.core

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easyprog.core.navigator.IntermediateNavigator
import com.easyprog.core.utils.Event
import com.easyprog.peil.R
import com.easyprog.peil.activity.MainActivity
import com.easyprog.core.views.BaseScreen
import com.easyprog.core.navigator.Navigator
import com.easyprog.core.uiactions.UiActions
import com.easyprog.core.utils.ResourceActions

const val ARG_SCREEN = "SCREEN"

class ActivityScopeViewModel(
    val uiActions: UiActions,
    val navigator: IntermediateNavigator
) : ViewModel(), Navigator by navigator, UiActions by uiActions {

    override fun onCleared() {
        super.onCleared()
        navigator.clear()
    }
}