package com.example.myfridge.ui.settings

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import com.example.myfridge.R
import com.example.myfridge.ui.home.HomeFragmentDirections
import com.quickersilver.themeengine.ThemeChooserDialogBuilder
import com.quickersilver.themeengine.ThemeEngine
import com.takisoft.preferencex.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var themeEngine: ThemeEngine
    override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MenuHost).addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater){
                menuInflater.inflate(R.menu.main_activity, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.action_add_fridge_item -> {
                        val destination = SettingsFragmentDirections.actionNavSettingsToThemeSettings()
                        findNavController().navigate(destination)
                        true
                    }

                    else -> (activity as AppCompatActivity).onOptionsItemSelected(menuItem)
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        themeEngine = ThemeEngine.getInstance(requireContext())
        val activity = (requireActivity() as AppCompatActivity)
        var toolbar: Toolbar = activity.findViewById(R.id.toolbar)
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val button = findPreference<Preference>("Color")
        button?.onPreferenceClickListener= Preference.OnPreferenceClickListener {
            ThemeChooserDialogBuilder(requireContext())
                .setTitle(R.string.menu_home)
                .setPositiveButton("OK") { _, theme ->
                    themeEngine.staticTheme = theme
                    requireActivity().recreate()
                }
                .setNegativeButton("Cancel")
                .setNeutralButton("Default") { _, _ ->
                    themeEngine.resetTheme()
                    requireActivity().recreate()
                }
                .setIcon(R.drawable.ic_round_brush)
                .create()
                .show()
            true

        }
        return super.onCreateView(inflater, container, savedInstanceState)


    }

}