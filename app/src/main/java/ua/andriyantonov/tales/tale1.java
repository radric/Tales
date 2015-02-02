package ua.andriyantonov.tales;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import static ua.andriyantonov.tales.R.drawable.play_select;

public class tale1 extends Activity implements MediaPlayer.OnPreparedListener {

    public static String TAG = "myLogs";

    MediaPlayer mediaPlayer;
    AudioManager am;
    ImageButton btnPlayResume;
    TextView taleName_txtvw,taleText_txtvw;
    String taleName_str;
    InputStream is;
    int play_flag=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tale1);


        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        btnPlayResume = (ImageButton) findViewById(R.id.btnPlayResume);

        taleName_txtvw = (TextView) findViewById(R.id.taleName);
        taleText_txtvw = (TextView) findViewById(R.id.teleText);

        Intent intent = getIntent();
        taleName_str = intent.getStringExtra("taleNameExtra");
        taleName_txtvw.setText(taleName_str);

        // reading the *.txt files
        AssetManager assetManager = getAssets();
        try {
            is = assetManager.open("tale1_text.txt");
            int size1 = is.available();
            byte[] buffer2 = new byte[size1];
            is.read(buffer2);
            is.close();

            String text = new String(buffer2);
            taleText_txtvw.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // player functions
    public void onPlayClick(View view)  {
        switch (view.getId()){
            case R.id.btnPlayResume:
                if (play_flag==0) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.tale1_audio);
                    mediaPlayer.start();
                    play_flag = 1;
                    btnPlayResume.setImageResource(R.drawable.pause_select);
                    break;
                } else if (play_flag == 1){
                mediaPlayer.pause();
                play_flag = 2;
                btnPlayResume.setImageResource(play_select);
                    break;
                } else if(play_flag==2)
                mediaPlayer.start();
                play_flag=1;
                btnPlayResume.setImageResource(R.drawable.pause_select);
                break;
            case R.id.btnStop:
                if (play_flag==1)
                mediaPlayer.stop();
                play_flag=0;
                btnPlayResume.setImageResource(play_select);
                break;
            case R.id.btnBackward:
                if (play_flag==1)
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;
            case R.id.btnForward:
                if (play_flag==1)
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
        }
    }

    // clean player data + play_flag=0+setPlay image
    private void releaseMP(){
        if (mediaPlayer!=null){
            Log.d(TAG,"mediaplayer = null");
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        play_flag=0;
        btnPlayResume.setImageResource(play_select);
    }

    // prepare player to play
    @Override
    public void onPrepared(MediaPlayer mp){
        mp.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tale1, menu);
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

    @Override
    public void onStart(){
        Log.d(TAG,"start");
        super.onStart();
        if (mediaPlayer==null){
        }else mediaPlayer.release();
    }
    @Override
    public void onPause(){
        Log.d(TAG,"pause");
        super.onPause();
        if (play_flag==1)
            mediaPlayer.pause();
    }
    @Override
    public void onStop(){
        Log.d(TAG,"stop");
        super.onStop();
        releaseMP();
    }
    @Override
    public void onDestroy(){
        Log.d(TAG, "destroy");
        super.onDestroy();

    }
}
