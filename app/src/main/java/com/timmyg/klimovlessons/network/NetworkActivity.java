package com.timmyg.klimovlessons.network;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timmyg.klimovlessons.R;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NetworkActivity extends AppCompatActivity {

    private EditText mHostEditText;
    private TextView mInfoTextView;
    private Button mIpButton;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        mHostEditText = findViewById(R.id.editTextHost);
        mInfoTextView = findViewById(R.id.textViewInfo);
        mListView = findViewById(R.id.listViewResult);
        mIpButton = findViewById(R.id.buttonIP);

        mIpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfoTextView.setText("Подождите...");
                new GetIPTask().execute();
            }
        });
    }

    private class GetIPTask extends AsyncTask<Void, Void, Void>{
        boolean error = false;
        String error_infp = "";
        InetAddress[] inetAddresses = null;

        List<String> hostList = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            getIP();
            return null;
        }

        private void getIP() {
            String host = mHostEditText.getText().toString();

            try {
                inetAddresses = InetAddress.getAllByName(host);

                for (InetAddress inetAdres: inetAddresses) {
                    hostList.add(inetAdres.getHostName() + "\n" + inetAdres.getHostAddress());
                                    }
            } catch (UnknownHostException e){
                e.printStackTrace();
                error = true;
                error_infp = e.toString();
            }
        }

        @Override
        protected void onPostExecute(Void result)  {
            if (error) {
                mInfoTextView.setText("Ошибка: \n" + error_infp);
            } else {
                mInfoTextView.setText("Финиш, приехали");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        NetworkActivity.this, android.R.layout.simple_list_item_1,
                        hostList);

                mListView.setAdapter(adapter);
            }
            super.onPostExecute(result);
        }


    }

}
