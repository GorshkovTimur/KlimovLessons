package com.timmyg.klimovlessons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.timmyg.klimovlessons.Graphic.Draw2D;

public class GraphicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Draw2D draw2D = new Draw2D(this);
        setContentView(draw2D);
    }
}
