package com.timmyg.klimovlessons.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.timmyg.klimovlessons.R;

public class FragmentActivity extends AppCompatActivity implements Fragment1.OnSelectedButtonListener{

    private boolean isDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment2 fragment2 = (Fragment2) fragmentManager.findFragmentById(R.id.fragment2);
        isDynamic = fragment2 == null || !fragment2.isInLayout();

        if (isDynamic) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment1 fragment1 = new Fragment1();
            fragmentTransaction.replace(R.id.container, fragment1, "fragment1");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment2 fragment2;

        if (isDynamic){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragment2 = new Fragment2();
            Bundle args = new Bundle();
            args.putInt(Fragment2.BUTTON_INDEX, buttonIndex);
            fragment2.setArguments(args);

            ft.replace(R.id.container, fragment2, "fragment2");
            ft.addToBackStack(null);
            ft.setCustomAnimations(
                    android.R.animator.fade_in, android.R.animator.fade_out);
            ft.commit();
        } else {
            fragment2 = (Fragment2) fragmentManager.findFragmentById(R.id.fragment2);
            fragment2.setDescription(buttonIndex);
        }
    }
}
