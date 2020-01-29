package com.timmyg.klimovlessons.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;

public class GPSActivity extends AppCompatActivity {

    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        PackageManager pm = this.getPackageManager();
        Toast.makeText(this, pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS) ? "Есть GPS"
                : "Нет GPS", Toast.LENGTH_LONG).show();

        infoTextView = findViewById(R.id.textview_gps);
        checkEnableGPS();

    }

    private void checkEnableGPS() {
        String provider = Settings.Secure.getString(getContentResolver(),Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (!provider.equals("")) {
            infoTextView.setText("GPS доступен " + provider);
        } else {
            infoTextView.setText("GPS выключен");
        }
    }

    public void OnButtonClick(View view) {
        Intent gpsOptionsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }
}
