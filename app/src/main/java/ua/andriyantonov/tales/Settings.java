package ua.andriyantonov.tales;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Settings extends Activity {
    RadioGroup rg;
    int checkedRB_INDEX;
    SharedPreferences shp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        rg=(RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton chekedRB = (RadioButton)rg.findViewById(checkedId);
                checkedRB_INDEX = rg.indexOfChild(chekedRB);
                savePref("rbVALUE",checkedRB_INDEX);
                loadSavedPref();
            }
        });
        loadSavedPref();
    }

    public void loadSavedPref() {
        shp = PreferenceManager.getDefaultSharedPreferences(this);
        int savedRB_INDEX = shp.getInt("rbVALUE",0);
        RadioButton savedCheckedRadioBtn = (RadioButton)rg.getChildAt(savedRB_INDEX);
        savedCheckedRadioBtn.setChecked(true);
    }

    public void savePref(String key, int value) {
        shp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = shp.edit();
        ed.putInt(key, value);
        ed.apply();
    }


    public void onClickTheme(View view) {
        switch (view.getId()){
            case R.id.chooseThemeDay:
                Utils.changeToTheme(this,Utils.THEME_DAY);
                break;
            case R.id.chooseThemeNight:
                Utils.changeToTheme(this,Utils.THEME_DAY);
                break;
        }
    }
}
