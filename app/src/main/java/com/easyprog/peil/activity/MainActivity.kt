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

//    private lateinit var navController: NavController
//    private lateinit var appBarConfiguration: AppBarConfiguration

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setContentView(binding.root)
    }

    private fun setupBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
    }

    private fun setupView() {
        //setup bottom navigation
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//        navController = navHostFragment.navController
//        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        disabledElements()
        super.onDestroy()
    }

    private fun disabledElements() {
        _binding = null
    }
}