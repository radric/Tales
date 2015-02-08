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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static ua.andriyantonov.tales.R.drawable.play_select;

public class TaleActivity extends Activity implements MediaPlayer.OnPreparedListener {

    private static String TAG = "myLogs";
    private final int REQUEST_CODE_TailActivity=2;

    private MediaPlayer mediaPlayer;
    private AudioManager am;
    private ImageButton btnPlayResume;
    private TextView taleName_txtvw;
    public TextView taleText_txtvw;
    private String taleName_str,action;
    private InputStream is;
    private AssetManager assetM;
    private Intent intent;
    private int play_flag=0,size;
    private byte[] buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // all of onCreate data must be in onStart
        // than preferences can be updated and themes can be changed
    }
    public void onStart(){
        super.onStart();
        Utils.onActivityCreateSetTheme(this);
        Utils.onTaleTextSetSize(this);
        setContentView(R.layout.activity_tales);

        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        btnPlayResume = (ImageButton) findViewById(R.id.btnPlayResume);

        taleName_txtvw = (TextView) findViewById(R.id.taleName);
        taleText_txtvw = (TextView) findViewById(R.id.taleText);

        Intent intent = getIntent();
        taleName_str = intent.getStringExtra("taleNameExtra");
        action = intent.getAction();
        taleName_txtvw.setText(taleName_str);

        getTaleText();

        if (mediaPlayer==null){
        }else mediaPlayer.release();
    }

    //get tale Text depending on action
    public void getTaleText() {
        assetM = getAssets();
        if (action.equals("ua.andriyantonov.tales.tale1")){
            // reading the *.txt files
            try {
                is = assetM.open("tale1_text.txt");
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
                is = assetM.open("tale2_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.equals("ua.andriyantonov.tales.tale3")){
            try {
                is = assetM.open("tale3_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.equals("ua.andriyantonov.tales.tale4")){
            try {
                is = assetM.open("tale4_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.equals("ua.andriyantonov.tales.tale5")){
            try {
                is = assetM.open("tale5_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.equals("ua.andriyantonov.tales.tale6")){
            try {
                is = assetM.open("tale6_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.equals("ua.andriyantonov.tales.tale7")){
            try {
                is = assetM.open("tale7_text.txt");
                size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
                String text = new String(buffer);
                taleText_txtvw.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // get tale Audio depending on action
    public void getTaleAudio(){
        if (action.equals("ua.andriyantonov.tales.tale1")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale1_audio);
        } else if (action.equals("ua.andriyantonov.tales.tale2")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale2_audio);
        } else if (action.equals("ua.andriyantonov.tales.tale3")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale3_audio);
        } else if (action.equals("ua.andriyantonov.tales.tale4")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale4_audio);
        } else if (action.equals("ua.andriyantonov.tales.tale5")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale5_audio);
        } else if (action.equals("ua.andriyantonov.tales.tale6")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale6_audio);
        } else if (action.equals("ua.andriyantonov.tales.tale7")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tale7_audio);
        }
    }

    // player functions
    public void onPlayClick(View view)  {
        switch (view.getId()){
            case R.id.btnPlayResume:
                if (play_flag==0||play_flag==2) {
                    getTaleAudio();
                    mediaPlayer.start();
                    play_flag = 1;
                    btnPlayResume.setImageResource(R.drawable.pause_select);
                    break;
                } else if (play_flag == 1){
                mediaPlayer.pause();
                play_flag = 2;
                btnPlayResume.setImageResource(R.drawable.play_select);
                } break;
            case R.id.btnStop:
                if (play_flag==1)
                mediaPlayer.stop();
                play_flag=0;
                btnPlayResume.setImageResource(R.drawable.play_select);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mainSettings:
                intent = new Intent(this, Preferences.class);
                startActivityForResult(intent,REQUEST_CODE_TailActivity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);
        onStart();
    }

    @Override
    public void onStop(){
        super.onStop();
        releaseMP();
    }

    @Override
    public void onBackPressed(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

}
