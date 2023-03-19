package com.example.myfridge.ui.settings

import android.os.Bundle
import com.example.myfridge.R
import com.takisoft.preferencex.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}