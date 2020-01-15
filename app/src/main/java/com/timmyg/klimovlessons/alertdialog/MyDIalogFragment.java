package com.timmyg.klimovlessons.alertdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.timmyg.klimovlessons.R;

public class MyDIalogFragment extends DialogFragment {

    String title = "Выберите кота";
    String message = "Выбери пищу";
    String button1String = "Вкусная пища";
    String button2String  = "Здоровая пища";
    String button3String = "Майонез";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setItems(R.array.cats, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        (AlertActivity)getActivity().click
                    }
                });

        return builder.create();
    }
}
