package com.example.myfridge

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.myfridge.databinding.ActivityMainBinding
import com.example.myfridge.ui.home.AddFoodFragment
import com.example.myfridge.ui.settings.SettingsActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainActivity.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController =
            findNavController(R.id.nav_host_fragment_content_main_activity)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_recipes, R.id.nav_shopping, R.id.nav_expiring, R.id.settingsActivity
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.settingsActivity -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_home -> {
                    val menu = binding.appBarMainActivity.toolbar.menu
                    menu.getItem(0).isVisible = true
                    navController.navigate(it.itemId)
                    drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    val menu = binding.appBarMainActivity.toolbar.menu
                    menu.getItem(0).isVisible = false
                    navController.navigate(it.itemId)
                    drawerLayout.closeDrawers()
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_fridge_item -> {
                val navController =
                    findNavController(R.id.nav_host_fragment_content_main_activity)

                navController.navigate(R.id.addFoodFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_main_activity)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onStart() {
        Log.d("OnStart", "Main Activity went through OnStart")
        super.onStart()
    }
    override fun onRestart() {
        Log.d("OnRestart", "Main Activity went through OnRestart")
        super.onRestart()
    }
    override fun onStop() {
        Log.d("OnStop", "Main Activity was Stopped")
        super.onStop()
    }
    override fun onPause() {
        Log.d("onPause", "Main Activity was paused")
        super.onPause()
    }
    override fun onResume() {
        super.onResume()
    }

}