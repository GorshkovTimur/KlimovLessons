package com.timmyg.klimovlessons.broadcasrt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;

public class TimingBroadcastActivity extends AppCompatActivity {

    private TimeBroadcastReciever timeBroadcastReciever = new TimeBroadcastReciever();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing_broadcast);
    }

    public void registerBroadcastReceiver(View view) {
        this.registerReceiver(timeBroadcastReciever,
                new IntentFilter("android.intent.action.TIME_TICK"));
        Toast.makeText(this, "Приемник включен", Toast.LENGTH_SHORT).show();
    }

    public void unregisterBroadcastReceiver(View view) {
        this.unregisterReceiver(timeBroadcastReciever);

        Toast.makeText(getApplicationContext(), "Приёмник выключён", Toast.LENGTH_SHORT)
                .show();
    }
}
