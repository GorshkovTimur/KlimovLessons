package com.timmyg.klimovlessons.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class NotepadActivity extends AppCompatActivity {

    private final String FILENAME = "sample.txt";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        editText = findViewById(R.id.notepad_edittext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notepad_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_open:
                openFile(FILENAME);
                return true;
            case R.id.action_save:
                saveFile(FILENAME);
                return true;
            case R.id.action_settings:
                Intent intent = new Intent();
                intent.setClass(this, NotepadSettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(getString(R.string.pref_openmode), false)){
            openFile(FILENAME);
        }
        float fSize = Float.parseFloat(preferences.getString(getString(R.string.pref_size), "20"));
        editText.setTextSize(fSize);
        String regular = preferences.getString(getString(R.string.pref_style),"");
        int typeface = Typeface.NORMAL;

        if (regular.contains("Полужирный"))
            typeface +=Typeface.BOLD;
        if (regular.contains("Курсив"))
            typeface +=Typeface.ITALIC;

        editText.setTypeface(null, typeface);
    }

    private void saveFile(String filename) {
        try {
            OutputStream outputStream = openFileOutput(filename, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(editText.getText().toString());
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(), "Exception: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void openFile(String filename) {
        try {
        InputStream inputStream = openFileInput(filename);

        if (inputStream!=null) {
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            inputStream.close();
            editText.setText(builder.toString());
        }
        } catch (Throwable throwable) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + throwable.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }




}
