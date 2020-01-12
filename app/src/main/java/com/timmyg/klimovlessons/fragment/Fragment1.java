package com.timmyg.klimovlessons.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.timmyg.klimovlessons.R;

public class Fragment1 extends Fragment implements View.OnClickListener {

    private Button button1, button2, button3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        button1 = rootView.findViewById(R.id.button1);
        button2 = rootView.findViewById(R.id.button2);
        button3 = rootView.findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onClick(View view) {
        int id = translateToIndex(view.getId());
        OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
        listener.onButtonSelected(id);
//        Toast.makeText(getActivity(), String.valueOf(id),
//                Toast.LENGTH_SHORT).show();
    }

    public int translateToIndex(int id){
        int index=-1;
        switch(id){
            case R.id.button1:
                index=1;
                break;
            case R.id.button2:
                index=2;
                break;
            case R.id.button3:
                index=3;
                break;
        }
        return index;
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex);
    }

}
