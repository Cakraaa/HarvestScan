package com.dicoding.harvestscan

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.harvestscan.databinding.ActivityMainBinding
import com.dicoding.harvestscan.ui.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_scan, R.id.navigation_my_plant
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mainViewModel.navigateToScan.observe(this) { navigate ->
            if (navigate) {
                navView.selectedItemId = R.id.navigation_scan
                mainViewModel.onNavigatedToScan()
            }
        }

        mainViewModel.navigateToHistory.observe(this) { navigate ->
            if (navigate) {
                navView.selectedItemId = R.id.navigation_scan
                navController.navigate(R.id.action_navigation_scan_to_navigation_history)
                mainViewModel.onNavigatedToHistory()
            }
        }

        mainViewModel.navigateToMyPlant.observe(this) { navigate ->
            if (navigate) {
                navView.selectedItemId = R.id.navigation_my_plant
                mainViewModel.onNavigatedToMyPlant()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}