package com.timmyg.klimovlessons.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.timmyg.klimovlessons.R;

public class ListViewActivity extends AppCompatActivity {

    private String[] titles = {  "00. Сколько времени",
            "01. Китайцы.",
            "02. Три семерки."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, titles));
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(ListViewActivity.this, DetailViewActivity.class);
                intent.putExtra("title", i);
                startActivity(intent);
            }
        });

    }
}
