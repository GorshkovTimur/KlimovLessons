package com.timmyg.klimovlessons.alertdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;

public class AlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        FragmentManager manager = getSupportFragmentManager();
        MyDIalogFragment myDIalogFragment = new MyDIalogFragment();
        myDIalogFragment.show(manager, "dialog");
    }

    public void clicked(){
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку OK!",
                Toast.LENGTH_LONG).show();
    }
}
