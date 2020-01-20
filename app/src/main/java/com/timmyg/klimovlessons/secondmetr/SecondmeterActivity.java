package com.timmyg.klimovlessons.secondmetr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.timmyg.klimovlessons.R;

public class SecondmeterActivity extends AppCompatActivity {

    private int seconds=0;

    private boolean isRunning;

    private boolean isWasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondmeter);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            isWasRunning = savedInstanceState.getBoolean("isWasRunning");
        }

        runTimer();
    }

    public void onStartClick(View view) {
        isRunning = true;
    }

    public void onStopClick(View view) {
        isRunning = false;
    }

    public void onResetClick(View view) {
        isRunning = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeTextView = findViewById(R.id.textViewTime);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60 ;
                int secs = seconds % 60 ;

                String time = String.format("%d:%02d:%02d", hours, minutes , secs);
                timeTextView.setText(time);
                if (isRunning){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
        outState.putBoolean("isWasRunning", isWasRunning);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isWasRunning = isRunning;
        isRunning = false;

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (isWasRunning) {
            isRunning = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isWasRunning = isRunning;
        isRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isWasRunning) {
            isRunning = true;
        }
    }
}
