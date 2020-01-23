package com.timmyg.klimovlessons.internalstorage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageActivity extends AppCompatActivity {

    EditText fileNameEditText;
    EditText contentEditText;
    ListView savedFilesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        fileNameEditText = findViewById(R.id.textview_filename);
        contentEditText  = findViewById(R.id.textview_filecontent);
        savedFilesListView = findViewById(R.id.listview_files);

        savedFilesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clickedFile = (String) adapterView.getItemAtPosition(i);
                openFileDialog(clickedFile);
            }
        });
    }

    private void openFileDialog(String clickedFile) {
        FileInputStream fis;
        StringBuilder content = new StringBuilder();
        try {
            fis = openFileInput(clickedFile);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                content.append(new String(input));
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        AlertDialog.Builder fileDialog = new AlertDialog.Builder(this);
        fileDialog.setTitle(clickedFile);
        TextView contentView = new TextView(this);
        contentView.setText(content.toString());
        ViewGroup.LayoutParams textViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        contentView.setLayoutParams(textViewLayoutParams);

        fileDialog.setView(contentView);
        fileDialog.setPositiveButton("OK", null);
        fileDialog.show();

    }

    public void onClick(View view) {
        String filename = fileNameEditText.getText().toString();
        String content = contentEditText.getText().toString();

        FileOutputStream fos;
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();

            Toast.makeText(getApplicationContext(),
                    "Файл " + filename + " сохранен",
                    Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickGetList(View view) {
        String[] savedFilesArray = fileList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                android.R.layout.simple_list_item_1,
                savedFilesArray);
        savedFilesListView.setAdapter(adapter);
    }

}
