package com.timmyg.klimovlessons.filemanager;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManagerActivity extends ListActivity {

    private List<String> pathList = null;
    private String root;
    private TextView pathTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        pathTextView = findViewById(R.id.textview_path);
        root = Environment.getRootDirectory().getPath();
        getDirs(root);
    }

    private void getDirs(String dirPath){
        pathTextView.setText("Путь: " + dirPath);
        List<String> itemList = new ArrayList<>();
        pathList = new ArrayList<>();
        File file = new File(dirPath);
        File[] fileInDir = file.listFiles();

        if (!dirPath.equals(root)) {
            itemList.add(root);
            pathList.add(root);
            itemList.add("../");
            pathList.add(file.getParent());
        }

        for (File fileArrat:fileInDir) {
            file = fileArrat;
            pathList.add(file.getPath());

            if (file.isDirectory())
                itemList.add(file.getName() + "/");
            else
                itemList.add(file.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.filemanager_list_item,
                itemList);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        File file = new File(pathList.get(position));
        if (file.isDirectory()){
            if (file.canRead())
                getDirs(pathList.get(position));
         else {
                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("[" + file.getName() + "] папка не доступна!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        } else {
            String fileInfo = "Абсолютный пусть: " + file.getAbsolutePath()
                    + "\n" + "Путь: " + file.getPath();

            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("[" + file.getName() + "]")
                    .setMessage(fileInfo)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
        }
    }
}
