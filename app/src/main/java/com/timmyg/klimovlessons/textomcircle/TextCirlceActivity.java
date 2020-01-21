package com.timmyg.klimovlessons.textomcircle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.timmyg.klimovlessons.R;

public class TextCirlceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CircleTextView(this));
    }
}
