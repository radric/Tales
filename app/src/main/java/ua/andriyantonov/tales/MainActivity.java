package ua.andriyantonov.tales;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.PowerManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends Activity {

    private PowerManager.WakeLock myWakeLock;
    private InputStreamReader is;
    private AssetManager assetM;
    private Intent intent ;
    private String taleName_str1,taleName_str2,taleName_str3,taleName_str4,taleName_str5,taleName_str6,
    taleName_str7;
    private final int REQUEST_CODE_MainActivity=1;
    private BufferedReader reader;

    private Button btn_tale1,btn_tale2,btn_tale3,btn_tale4,btn_tale5,btn_tale6,btn_tale7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // all of onCreate data must be in onStart
        // than preferences can be updated and themes can be changed
    }
    public void onStart(){
        super.onStart();
        Log.d("myLogs","Utils");
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        onWakeLock();

        btn_tale1 = (Button) findViewById(R.id.btn_tale1);
        btn_tale2 = (Button) findViewById(R.id.btn_tale2);
        btn_tale3 = (Button) findViewById(R.id.btn_tale3);
        btn_tale4 = (Button) findViewById(R.id.btn_tale4);
        btn_tale5 = (Button) findViewById(R.id.btn_tale5);
        btn_tale6 = (Button) findViewById(R.id.btn_tale6);
        btn_tale7 = (Button) findViewById(R.id.btn_tale7);

        getTaleNames();
    }

    public void onClickRead(View view) {
        switch (view.getId()){
            case R.id.btn_tale1:
                intent = new Intent("ua.andriyantonov.tales.tale1");
                intent.putExtra("taleNameExtra",taleName_str1);
                startActivity(intent);
                break;
            case R.id.btn_tale2:
                intent = new Intent("ua.andriyantonov.tales.tale2");
                intent.putExtra("taleNameExtra",taleName_str2);
                startActivity(intent);
                break;
            case R.id.btn_tale3:
                intent = new Intent("ua.andriyantonov.tales.tale3");
                intent.putExtra("taleNameExtra",taleName_str3);
                startActivity(intent);
                break;
            case R.id.btn_tale4:
                intent = new Intent("ua.andriyantonov.tales.tale4");
                intent.putExtra("taleNameExtra",taleName_str4);
                startActivity(intent);
                break;
            case R.id.btn_tale5:
                intent = new Intent("ua.andriyantonov.tales.tale5");
                intent.putExtra("taleNameExtra",taleName_str5);
                startActivity(intent);
                break;
            case R.id.btn_tale6:
                intent = new Intent("ua.andriyantonov.tales.tale6");
                intent.putExtra("taleNameExtra",taleName_str6);
                startActivity(intent);
                break;
            case R.id.btn_tale7:
                intent = new Intent("ua.andriyantonov.tales.tale7");
                intent.putExtra("taleNameExtra",taleName_str7);
                startActivity(intent);
                break;
        }
    }

//get tale names from txt files from /main/assets
    private void getTaleNames() {
        assetM = getAssets();
        try {
            //taleName1
            is = new InputStreamReader(assetM.open("tale1_name.txt"));
            reader = new BufferedReader(is);
            taleName_str1 = reader.readLine();
            btn_tale1.setText(taleName_str1);

            //taleName2
            is = new InputStreamReader(assetM.open("tale2_name.txt"));
            reader = new BufferedReader(is);
            taleName_str2 = reader.readLine();
            btn_tale2.setText(taleName_str2);

            //taleName3
            is = new InputStreamReader(assetM.open("tale3_name.txt"));
            reader = new BufferedReader(is);
            taleName_str3 = reader.readLine();
            btn_tale3.setText(taleName_str3);

            //taleName4
            is = new InputStreamReader(assetM.open("tale4_name.txt"));
            reader = new BufferedReader(is);
            taleName_str4 = reader.readLine();
            btn_tale4.setText(taleName_str4);

            //taleName5
            is = new InputStreamReader(assetM.open("tale5_name.txt"));
            reader = new BufferedReader(is);
            taleName_str5 = reader.readLine();
            btn_tale5.setText(taleName_str5);

            //taleName6
            is = new InputStreamReader(assetM.open("tale6_name.txt"));
            reader = new BufferedReader(is);
            taleName_str6 = reader.readLine();
            btn_tale6.setText(taleName_str6);

            //taleName6
            is = new InputStreamReader(assetM.open("tale7_name.txt"));
            reader = new BufferedReader(is);
            taleName_str7 = reader.readLine();
            btn_tale7.setText(taleName_str7);


            reader.close();

//            //taleName1
//            is = am.open("tale1_name.txt");
//            size = is.available();
//            buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            taleName_str1 = new String(buffer);
//            btn_tale1.setText(taleName_str1);

            //taleName2
//            is=am.open("tale2_name.txt");
//            size=is.available();
//            buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            taleName_str2=new String(buffer);
//            btn_tale2.setText(taleName_str2);
//
//            //taleName3
//            is=am.open("tale2_name.txt");
//            size=is.available();
//            buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            taleName_str2=new String(buffer);
//            btn_tale3.setText(taleName_str2);
//
//            //taleName4
//            is=am.open("tale2_name.txt");
//            size=is.available();
//            buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            taleName_str2=new String(buffer);
//            btn_tale4.setText(taleName_str2);
//
//            //taleName5
//            is=am.open("tale2_name.txt");
//            size=is.available();
//            buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            taleName_str2=new String(buffer);
//            btn_tale5.setText(taleName_str2);
//
//            //taleName6
//            is=am.open("tale2_name.txt");
//            size=is.available();
//            buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            taleName_str2=new String(buffer);
//            btn_tale6.setText(taleName_str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
        switch (id) {
            case R.id.mainSettings:
                intent = new Intent(this, Preferences.class);
                startActivityForResult(intent,REQUEST_CODE_MainActivity);
                super.onDestroy();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //return intent-action from preferences
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);
        onStart();
    }

//    This code together with the one in onDestroy()
//    will make the screen be always on until this Activity gets destroyed
    private void onWakeLock(){
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.myWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My tag");
        this.myWakeLock.acquire();
    }

    //alert to ask R user shure that he want to exit
    public void onBackPressed(){
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.adb_setTitle);
        adb.setMessage(R.string.adb_setMessage);
        adb.setPositiveButton(R.string.adb_setPositive,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
        adb.setNegativeButton(R.string.adb_setNegative,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.show();
    }
}
