package com.timmyg.klimovlessons.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.timmyg.klimovlessons.R;
import com.timmyg.klimovlessons.sqlite.data.HotelContract;
import com.timmyg.klimovlessons.sqlite.data.HotelDbHelper;


public class BasicActivity extends AppCompatActivity {

    private HotelDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BasicActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new HotelDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                HotelContract.GuestEntry._ID,
                HotelContract.GuestEntry.COLUMN_NAME,
                HotelContract.GuestEntry.COLUMN_CITY,
                HotelContract.GuestEntry.COLUMN_GENDER,
                HotelContract.GuestEntry.COLUMN_AGE
        };

        Cursor cursor = db.query(
                HotelContract.GuestEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayTextView = findViewById(R.id.text_view_info);

        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " гостей.\n\n");
            displayTextView.append(HotelContract.GuestEntry._ID + " - " +
                    HotelContract.GuestEntry.COLUMN_NAME + " - " +
                    HotelContract.GuestEntry.COLUMN_CITY + " - " +
                    HotelContract.GuestEntry.COLUMN_GENDER + " - " +
                    HotelContract.GuestEntry.COLUMN_AGE + "\n");

            int idColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_NAME);
            int cityColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_CITY);
            int genderColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_GENDER);
            int ageColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_AGE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentCity = cursor.getString(cityColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentAge = cursor.getInt(ageColumnIndex);

                displayTextView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentCity + " - " +
                        currentGender + " - " +
                        currentAge));
            }
        } finally {
            cursor.close();
        }


    }
}
