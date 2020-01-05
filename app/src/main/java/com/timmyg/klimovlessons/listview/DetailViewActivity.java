package com.timmyg.klimovlessons.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.timmyg.klimovlessons.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DetailViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        WebView webView = findViewById(R.id.webview);

        Intent intent = getIntent();

        String resName = "n"+ intent.getIntExtra("title",0);
        Context context = getBaseContext();

        String text = readRawFile(context, getResources().getIdentifier(resName, "raw", "com.timmyg.simplepaint"));
        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);
    }

    private String readRawFile(Context context, int resId){
        InputStream inputStream = context.getResources().openRawResource(resId);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        StringBuilder builder = new StringBuilder();

        while (true) {
            try {
                if ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            return builder.toString();

        }
    }
}
