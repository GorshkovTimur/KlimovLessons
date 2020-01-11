package com.timmyg.klimovlessons.broadcasrt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MessageReciever extends BroadcastReceiver {
    public MessageReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Обнаружено сообщение: " +
                intent.getStringExtra("ru.alexanderklimov.broadcast.Message"),
                Toast.LENGTH_LONG).show();
    }
}
