package com.timmyg.klimovlessons.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.TextView;

import com.timmyg.klimovlessons.R;

public class CustomViewActivity extends Activity {

    boolean customTitleSupported;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_custom_view);
        customTitleBar("Лох", "Пидр");
               TextView title = getWindow()
                .findViewById(android.R.id.title);
        if (title != null) {
            // цвет в заголовке
            title.setTextColor(Color.YELLOW);
            // find parent view
            ViewParent parent = title.getParent();
            if (parent != null && (parent instanceof View)) {
                View parentView = (View) parent;
                parentView.setBackgroundColor(Color.CYAN);
            }
        }


    }

    private void customTitleBar(String first, String second) {

        if (second.length() > 20){
            second.substring(0,20);
        }
        if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.customtitilebar);
            TextView leftTextView = findViewById(R.id.titletvleft);
            TextView rightTextView = findViewById(R.id.titletvright);
            leftTextView.setText(first);
            rightTextView.setText(second);
        }

    }
}
