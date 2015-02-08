package ua.andriyantonov.tales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.widget.TextView;

public class Preferences extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("myLogs","onCreate");
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
    @Override
    public void onResume(){
        Log.d("myLogs","onResume");
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause(){
        Log.d("myLogs","onPause");
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences shp, String key) {
        ListPreference listPref1 = (ListPreference)findPreference(getString(R.string.ListPref_key_1));
        Log.d("myLogs","onTheme");
        CharSequence currText = listPref1.getEntry();
        if (currText.toString().contains(getString(R.string.chooseThemeDayText))){
            Utils.changeToTheme(this,Utils.THEME_DAY);
        } else if (currText.toString().contains(getString(R.string.chooseThemeNightText))){
            Utils.changeToTheme(this,Utils.THEME_NIGHT);
        }
        ListPreference listPref2 = (ListPreference)findPreference(getString(R.string.ListPref_key_2));
        CharSequence currFontSize = listPref2.getEntry();
        if (currFontSize.toString().contains(getString(R.string.ListPref_array_fontSize_small))){
            Log.d("myLogs","onSmall");
            Utils.changeTaleTextSize(this,Utils.TALE_TEXTSIZE_SMALL);
        } else if (currFontSize.toString().contains(getString(R.string.ListPref_array_fontSize_midle))){
            Log.d("myLogs","onMidle");
            Utils.changeTaleTextSize(this, Utils.TALE_TEXTSIZE_MIDLE);
        } else if (currFontSize.toString().contains(getString(R.string.ListPref_array_fontSize_big))){
            Log.d("myLogs","onBig");
            Utils.changeTaleTextSize(this, Utils.TALE_TEXTSIZE_BIG);
        } else if (currFontSize.toString().contains(getString(R.string.ListPref_array_fontSize_huge))){
            Log.d("myLogs","onBig");
            Utils.changeTaleTextSize(this, Utils.TALE_TEXTSIZE_HUGE);
        }
    }

    public void onBackPressed(){
        Log.d("myLogs","onBackPressed");
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
