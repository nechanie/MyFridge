package com.example.myfridge

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.get
import androidx.navigation.ui.*
import com.example.myfridge.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.mikepenz.materialdrawer.model.NavigationDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.withName
import com.mikepenz.materialdrawer.util.addItems
import com.mikepenz.materialdrawer.util.getDrawerItem
import com.mikepenz.materialdrawer.util.inflateMenu
import com.mikepenz.materialdrawer.util.setupWithNavController
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView
import com.quickersilver.themeengine.ThemeEngine
import com.quickersilver.themeengine.ThemeMode

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var slider: MaterialDrawerSliderView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeEngine.applyToActivity(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val themeEngine = ThemeEngine.getInstance(this)
        setSupportActionBar(binding.appBarMainActivity.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.root,
            binding.appBarMainActivity.toolbar,
            com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )
        binding.root.addDrawerListener(actionBarDrawerToggle)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        navController =
            findNavController(R.id.nav_host_fragment_content_main_activity)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_recipes, R.id.nav_shopping, R.id.nav_expiring
            ), drawerLayout
        )
        slider = binding.slider
        setupActionBarWithNavController(navController, appBarConfiguration)
//        binding.slider.setupWithNavController(navController, { view, item, int -> Log.d("NAVLISTEN", "${item}\n${view}\n${int}"); true}, fallBackListener = { view, item, int -> Log.d("NAVFALLBACK", "${item}\n${view}\n${int}"); true})
        slider.apply {
            setSelectionAtPosition(0, false)
            inflateMenu(R.menu.activity_main_drawer)
            onDrawerItemClickListener = {_, drawerItem, _ ->
                onNavSupportedMenuItemSelected(drawerItem).also {
                    if (it){
                        this.drawerLayout!!.close()
                    }
                }

            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                selectExtension.deselect(selectExtension.selections.toMutableSet())
                setSelection(destination.id.toLong(), false)

            }
        }



    }



    override fun onResume() {
        super.onResume()
        actionBarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    private fun onNavSupportedMenuItemSelected(menuItem: IDrawerItem<*>): Boolean{
        try {
            if (navController.findDestination(menuItem.identifier.toInt()) == null) {
                return false
            }
            navController.navigate(menuItem.identifier.toInt())
            return true
        }
        catch (e: Exception){
            return false
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_main_activity)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}