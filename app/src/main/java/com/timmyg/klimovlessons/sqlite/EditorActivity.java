package com.timmyg.klimovlessons.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;
import com.timmyg.klimovlessons.sqlite.data.HotelContract;
import com.timmyg.klimovlessons.sqlite.data.HotelDbHelper;

public class EditorActivity extends AppCompatActivity {

    private Button saveButton;

    private EditText nameEditText;
    private EditText cityEditText;
    private EditText ageEditText;

    private Spinner genderSpinner;

    private int gender = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        nameEditText = findViewById(R.id.edut_guest_name);
        cityEditText = findViewById(R.id.edit_guest_city);
        ageEditText = findViewById(R.id.edit_guest_age);
        genderSpinner = findViewById(R.id.spinner_gender);
        saveButton = findViewById(R.id.button_save);

        setupSinner();

    }

    private void setupSinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setSelection(2);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_female))) {
                        gender = HotelContract.GuestEntry.GENDER_FEMALE; // Кошка
                    } else if (selection.equals(getString(R.string.gender_male))) {
                        gender = HotelContract.GuestEntry.GENDER_MALE; // Кот
                    } else {
                        gender = HotelContract.GuestEntry.GENDER_UNKNOWN; // Не определен
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gender = 2;
            }
        });
    }

    public void saveDatabase(View view) {
        String name = nameEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String ageString = ageEditText.getText().toString().trim();
        int age = Integer.parseInt(ageString);

        SQLiteDatabase db = new HotelDbHelper(this).getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(HotelContract.GuestEntry.COLUMN_NAME, name);
        values.put(HotelContract.GuestEntry.COLUMN_CITY, city);
        values.put(HotelContract.GuestEntry.COLUMN_GENDER, gender);
        values.put(HotelContract.GuestEntry.COLUMN_AGE, age);

        long newRowId = db.insert(HotelContract.GuestEntry.TABLE_NAME, null, values);

        if (newRowId == -1){
            Toast.makeText(this, "Ошибка при заведении гостя", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Гость заведён под номером: " + newRowId, Toast.LENGTH_SHORT).show();
        }
        finish();
        
    }
}
