package ua.andriyantonov.tales;

import android.app.Activity;
import android.content.Intent;

public class Utils {

    private static int sTheme;
    private static int sSize;
    public final static int THEME_DAY = 0;
    public final static int THEME_NIGHT = 1;
    public final static int TALE_TEXTSIZE_SMALL = 2;
    public final static int TALE_TEXTSIZE_MIDLE =3;
    public final static int TALE_TEXTSIZE_BIG =4;
    public final static int TALE_TEXTSIZE_HUGE =5;

    //Set the theme of the Activity, and restart it by creating a new Activity of the same type.
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void changeTaleTextSize(Activity activity, int size) {
        sSize = size;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }


    // Set the theme of the activity, according to the configuration.
    public static void onActivityCreateSetTheme(Activity activity){
        switch (sTheme){
            default:
            case THEME_DAY:
                activity.setTheme(R.style.ThemeDay);
                break;
            case THEME_NIGHT:
                activity.setTheme(R.style.ThemeNight);
                break;
        }
    }
    public static void onTaleTextSetSize(Activity activity){
        switch (sSize){
            default:
            case TALE_TEXTSIZE_MIDLE:
                activity.setTheme(R.style.taleTextFontSize_midle);
                break;
            case TALE_TEXTSIZE_SMALL:
                activity.setTheme(R.style.taleTextFontSize_small);
                break;
            case TALE_TEXTSIZE_BIG:
                activity.setTheme(R.style.taleTextFontSize_big);
                break;
            case TALE_TEXTSIZE_HUGE:
                activity.setTheme(R.style.taleTextFontSize_huge);
                break;
        }
    }
}

