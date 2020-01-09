package com.timmyg.klimovlessons.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.timmyg.klimovlessons.R;

public class GeoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        String geoURIString = "google.streetview:cbll=59.939448,30.328264&cbp=1,99.56,,1,2.0&mz=19";
        Uri geoUri = Uri.parse(geoURIString);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
        startActivity(mapIntent);
    }
}
