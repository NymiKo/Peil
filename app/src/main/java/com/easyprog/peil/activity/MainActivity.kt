package com.easyprog.peil.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easyprog.core.ActivityScopeViewModel
import com.easyprog.core.navigator.IntermediateNavigator
import com.easyprog.core.navigator.Navigator
import com.easyprog.core.navigator.StackFragmentNavigator
import com.easyprog.core.uiactions.AndroidUiActions
import com.easyprog.core.utils.viewModelCreator
import com.easyprog.core.views.FragmentsHolder
import com.easyprog.peil.R
import com.easyprog.peil.databinding.ActivityMainBinding
import com.easyprog.peil.fragments.bottom_navigation.BottomNavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}