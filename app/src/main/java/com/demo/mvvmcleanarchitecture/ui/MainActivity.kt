package com.demo.mvvmcleanarchitecture.ui

/**
 * Created by Safa NAOUI on 26/06/2020.
 */

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.demo.mvvmcleanarchitecture.R
import com.demo.mvvmcleanarchitecture.common.BaseActivity
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.home_fragment, R.id.search_fragment
        )
            .setDrawerLayout(drawerLayout)
            .build()
        navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp())
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout.closeDrawers()
        when (menuItem.itemId) {
            R.id.home ->
                navController.navigate(R.id.home_fragment)
            R.id.search ->
                navController.navigate(R.id.search_fragment)
        }
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {

    }
}
