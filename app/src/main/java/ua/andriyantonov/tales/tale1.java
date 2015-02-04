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
    String taleName_str,action;
    InputStream is;
    Intent intent;
    int play_flag=0,size;
    byte[] buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_tale1);

        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        btnPlayResume = (ImageButton) findViewById(R.id.btnPlayResume);

        taleName_txtvw = (TextView) findViewById(R.id.taleName);
        taleText_txtvw = (TextView) findViewById(R.id.teleText);

        Intent intent = getIntent();
        taleName_str = intent.getStringExtra("taleNameExtra");
        action = intent.getAction();
        taleName_txtvw.setText(taleName_str);

        getTaleText();

    }

    //get tale Text depending on action
    public void getTaleText() {
        AssetManager assetManager = getAssets();
        if (action.equals("ua.andriyantonov.tales.tale1")){
            // reading the *.txt files
            try {
                is = assetManager.open("tale1_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (action.equals("ua.andriyantonov.tales.tale2")){
            try {
                is = assetManager.open("tale2_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (play_flag==0) {
                mediaPlayer = MediaPlayer.create(this, R.raw.tale2_audio);}
        }
    }

    // get tale Audio depending on action
    public void getTaleAudio(){
        if (action.equals("ua.andriyantonov.tales.tale1")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale1_audio);
        } else if (action.equals("ua.andriyantonov.tales.tale2")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale2_audio);
        }
    }

    // player functions
    public void onPlayClick(View view)  {
        switch (view.getId()){
            case R.id.btnPlayResume:
                if (play_flag==0) {
                    getTaleAudio();
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            intent = new Intent(this,Settings.class);
            startActivity(intent);
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

}
