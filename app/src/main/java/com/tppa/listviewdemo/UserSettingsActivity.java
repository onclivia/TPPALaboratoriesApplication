package com.tppa.listviewdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;

import java.io.FileOutputStream;

public class UserSettingsActivity extends PreferenceActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName("user_settings_edit_preferences");
        addPreferencesFromResource(R.xml.user_settings_edit_preferences);

    }



}
