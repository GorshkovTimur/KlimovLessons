package com.timmyg.klimovlessons.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.timmyg.klimovlessons.R;

public class PlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playPauseButton;
    private Button stopButton;

    private TextView stateTextView;

    private int mediaplayerState;
    private final int MP_STATE_NOTSTARTER = 0;
    private final int MP_STATE_PLAYING = 1;
    private final int MP_STATE_PAUSING = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mediaPlayer = MediaPlayer.create(this, R.raw.jack);

        stateTextView = findViewById(R.id.tv_status);
        playPauseButton = findViewById(R.id.btn_play_pause);
        stopButton = findViewById(R.id.btn_stop);
        playPauseButton.setOnClickListener(onPlayPauseClickListener);
        stopButton.setOnClickListener(onStopClickListener);
    }

    Button.OnClickListener onPlayPauseClickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (mediaplayerState){
                case MP_STATE_NOTSTARTER:
                    mediaPlayer.start();
                    playPauseButton.setText("Пауза");
                    stateTextView.setText("- Проигрывается -");
                    mediaplayerState = MP_STATE_PLAYING;
                    break;
                case MP_STATE_PLAYING:
                    mediaPlayer.pause();
                    playPauseButton.setText("Проигрывание");
                    stateTextView.setText("- Приостановлено -");
                    mediaplayerState = MP_STATE_PAUSING;
                    break;
                case MP_STATE_PAUSING:
                    mediaPlayer.start();
                    playPauseButton.setText("Пауза");
                    stateTextView.setText("- Проигрывается -");
                    mediaplayerState = MP_STATE_PAUSING;
                    break;
            }
        }
    };

    Button.OnClickListener onStopClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            mediaPlayer.stop();
            mediaPlayer.release();
            finish();
        }
    };

    
}
