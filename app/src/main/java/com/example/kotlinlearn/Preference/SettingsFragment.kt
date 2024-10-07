package com.example.kotlinlearn.Preference


import android.os.Bundle
import android.util.Log
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.kotlinlearn.R


class SettingsFragment : Preference.OnPreferenceClickListener,
    Preference.OnPreferenceChangeListener, PreferenceFragmentCompat() {
    var TAG = "SettingsFragment"
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
//        val findPreference = findPreference<EditTextPreference>("edittext_preference")
//        findPreference?.onPreferenceClickListener = this
//        findPreference?.onPreferenceChangeListener = this
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        Log.d(TAG, "onPreferenceClick: ")
        return false
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        Log.d(TAG, "onPreferenceChange: ")
        return false
    }
}
