package com.example.newfeedsapp.ui

import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.newfeedsapp.R
import com.example.newfeedsapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.newfeeds.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun initLayout() {
        setupDrawerLayout()
        handleDrawerItemClick()
    }

    private fun setupDrawerLayout() {
        setSupportActionBar(binding.toolbar)
        binding.navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
    }

    // Fire the toast with menu item title then closing the drawer after acting on it
    private fun handleDrawerItemClick() =
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            Toast.makeText(this@MainActivity, menuItem.title, Toast.LENGTH_SHORT).show()
            onNavDestinationSelected(menuItem, navController)
            //This is for closing the drawer after acting on it
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    override fun onSupportNavigateUp(): Boolean  = NavigationUI.navigateUp(navController, binding.drawerLayout)

    override fun onBackPressed() = binding.drawerLayout.closeDrawer(GravityCompat.START)
        .takeIf { binding.drawerLayout.isDrawerOpen(GravityCompat.START) } ?: super.onBackPressed()

}