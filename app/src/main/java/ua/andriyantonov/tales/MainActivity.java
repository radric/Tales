package ua.andriyantonov.tales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.PowerManager;
import android.os.Bundle;
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
    Intent intent ;
    public String taleName_str1,taleName_str2;

    Button btn_tale1,btn_tale2,btn_tale3,btn_tale4,btn_tale5,btn_tale6;
    int size;
    byte[] buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        onWakeLock();

        btn_tale1 = (Button) findViewById(R.id.btn_tale1);
        btn_tale2 = (Button) findViewById(R.id.btn_tale2);
        btn_tale3 = (Button) findViewById(R.id.btn_tale3);
        btn_tale4 = (Button) findViewById(R.id.btn_tale4);
        btn_tale5 = (Button) findViewById(R.id.btn_tale5);
        btn_tale6 = (Button) findViewById(R.id.btn_tale6);

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
                intent = new Intent("ua.andriyantonov.tales.tale2");
                intent.putExtra("taleNameExtra",taleName_str2);
                startActivity(intent);
                break;
            case R.id.btn_tale4:
                intent = new Intent("ua.andriyantonov.tales.tale2");
                intent.putExtra("taleNameExtra",taleName_str2);
                startActivity(intent);
                break;
            case R.id.btn_tale5:
                intent = new Intent("ua.andriyantonov.tales.tale2");
                intent.putExtra("taleNameExtra",taleName_str2);
                startActivity(intent);
                break;
            case R.id.btn_tale6:
                intent = new Intent("ua.andriyantonov.tales.tale2");
                intent.putExtra("taleNameExtra",taleName_str2);
                startActivity(intent);
                break;
        }
    }

// get tale names from txt files from /main/assets
    private void getTaleNames() {
        am = getAssets();
        try {
            //taleName1
            is = am.open("tale1_name.txt");
            size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str1 = new String(buffer);
            btn_tale1.setText(taleName_str1);

            //taleName2
            is=am.open("tale2_name.txt");
            size=is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str2=new String(buffer);
            btn_tale2.setText(taleName_str2);

            //taleName3
            is=am.open("tale2_name.txt");
            size=is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str2=new String(buffer);
            btn_tale3.setText(taleName_str2);

            //taleName4
            is=am.open("tale2_name.txt");
            size=is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str2=new String(buffer);
            btn_tale4.setText(taleName_str2);

            //taleName5
            is=am.open("tale2_name.txt");
            size=is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str2=new String(buffer);
            btn_tale5.setText(taleName_str2);

            //taleName6
            is=am.open("tale2_name.txt");
            size=is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            taleName_str2=new String(buffer);
            btn_tale6.setText(taleName_str2);
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
        switch (id) {
            case R.id.mainSettings:
                intent = new Intent(this, Preferences.class);
                startActivity(intent);
                break;
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
    public void onBackPressed(){
        moveTaskToBack(true);
        finish();
    }
}
