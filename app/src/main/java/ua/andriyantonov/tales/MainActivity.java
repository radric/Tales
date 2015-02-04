package ua.andriyantonov.tales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.PowerManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {

    public PowerManager.WakeLock myWakeLock;
    InputStream is;
    AssetManager am;
    Intent intent;
    public String taleName_str1,taleName_str2;

    Button btn_tale1,btn_tale2;
    int size;
    byte[] buffer;
    public static String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        onWakeLock();

        btn_tale1 = (Button) findViewById(R.id.btn_tale1);
        btn_tale2 = (Button) findViewById(R.id.btn_tale2);

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
        }
    }

    private void getTaleNames() {
        am = getAssets();
        try {
            is = am.open("tale1_name.txt");
            size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str1 = new String(buffer);
            btn_tale1.setText(taleName_str1);

            is=am.open("tale2_name.txt");
            size=is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str2=new String(buffer);
            btn_tale2.setText(taleName_str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
         if (id == R.id.action_settings) {
            intent = new Intent(this,Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


//    This code together with the one in onDestroy()
//    will make the screen be always on until this Activity gets destroyed
    private void onWakeLock(){
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.myWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My tag");
        this.myWakeLock.acquire();
    }
    @Override
    public void onDestroy(){
        this.myWakeLock.release();
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
}
