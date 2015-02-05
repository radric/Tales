package ua.andriyantonov.tales;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.os.Bundle;

public class Preferences extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
//final static String TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
    @Override
    public void onResume(){
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        ListPreference listPref = (ListPreference)findPreference(getString(R.string.ListPref_key_1));
        CharSequence currText = listPref.getEntry();
        if (currText.toString().contains(getString(R.string.chooseThemeDayText))){
            Utils.changeToTheme(this,Utils.THEME_DAY);
        } else if (currText.toString().contains(getString(R.string.chooseThemeNightText))){
            Utils.changeToTheme(this,Utils.THEME_NIGHT);
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
