package com.timmyg.klimovlessons.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.timmyg.klimovlessons.R;

public class Fragment2 extends Fragment {

    private TextView infoTextView;
    private ImageView catImageView;
    private String[] catDescription;

    public static final String BUTTON_INDEX = "button_index";
    public static final int BUTTON_INDEX_DEAFAULT = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2, container, false);

        infoTextView = rootView.findViewById(R.id.textView);
        catImageView = rootView.findViewById(R.id.imageView);

        catDescription = getResources().getStringArray(R.array.cats);

        Bundle args = getArguments();
        int buttonIndex = args != null ? args.getInt(BUTTON_INDEX, BUTTON_INDEX_DEAFAULT)
                : BUTTON_INDEX_DEAFAULT;
        if (buttonIndex != BUTTON_INDEX_DEAFAULT)
            setDescription(buttonIndex);

        return rootView;
    }

        public void setDescription(int buttonIndex){
        String catDescriptionString = catDescription[buttonIndex];
        infoTextView.setText(catDescriptionString);

        switch (buttonIndex){
            case 1:
                catImageView.setImageResource(android.R.drawable.alert_dark_frame);
                break;
            case 2:
                catImageView.setImageResource(android.R.drawable.arrow_down_float);
                break;
            case 3:
                catImageView.setImageResource(android.R.drawable.arrow_up_float);
                break;
        }
        }
}
