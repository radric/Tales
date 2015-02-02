package ua.andriyantonov.tales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
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
    Intent intentToTale;
    public String taleName_str;

    Button btn_tale1;
    public static String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onWakeLock();

        btn_tale1 = (Button) findViewById(R.id.btn_tale1);



        am = getAssets();
        try {
            is = am.open("tale1_name.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            taleName_str = new String(buffer);
            btn_tale1.setText(taleName_str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickRead(View view) {
        switch (view.getId()){
            case R.id.btn_tale1:
                intentToTale = new Intent(MainActivity.this,tale1.class);
                intentToTale.putExtra("taleNameExtra",taleName_str);
                startActivity(intentToTale);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
    public void onStop(){
        Log.d(TAG,"onStop");
        super.onStop();
    }
    @Override
    public void onDestroy(){
        this.myWakeLock.release();
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


}
