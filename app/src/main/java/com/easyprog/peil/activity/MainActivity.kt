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
class MainActivity : AppCompatActivity(), FragmentsHolder {

//    private lateinit var navController: NavController
//    private lateinit var appBarConfiguration: AppBarConfiguration

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navigator: StackFragmentNavigator

    private val activityViewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            uiActions = AndroidUiActions(applicationContext),
            navigator = IntermediateNavigator()
        )
    }

    override fun getActivityScopeViewModel(): ActivityScopeViewModel {
        return activityViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        createAndSetupNavigator(savedInstanceState)
        setupView()
        setContentView(binding.root)
    }

    private fun setupBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
    }

    private fun createAndSetupNavigator(savedInstanceState: Bundle?) {
        navigator = StackFragmentNavigator(
            activity = this,
            containerId = R.id.nav_host_fragment_container,
            initialScreenCreator = { BottomNavigationFragment.Screen() }
        )
        navigator.onCreate(savedInstanceState)
    }

    private fun setupView() {
        //setup bottom navigation
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//        navController = navHostFragment.navController
//        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        setTargetNavigator(navigator)
    }

    private fun setTargetNavigator(navigator: Navigator?) {
        activityViewModel.navigator.setTarget(navigator)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPause() {
        super.onPause()
        setTargetNavigator(null)
    }

    override fun onDestroy() {
        disabledElements()
        super.onDestroy()
    }

    private fun disabledElements() {
        _binding = null
        navigator.onDestroy()
    }
}