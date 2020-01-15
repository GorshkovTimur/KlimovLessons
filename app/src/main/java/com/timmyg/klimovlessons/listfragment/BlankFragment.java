package com.timmyg.klimovlessons.listfragment;


import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.timmyg.klimovlessons.R;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends ListFragment {

    final String[] catNames = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
            "Кузя", "Китти", "Масяня", "Симба"};

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, catNames);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, null);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String prompt = "Вы выбрали: " +
                getListView().getItemAtPosition(position).toString() + "\n";
        prompt += "Выбранные элементы: \n";
        int count = getListView().getCount();
        SparseBooleanArray sparseBooleanArray = getListView()
                .getCheckedItemPositions();
        for (int i = 0; i < count ; i++) {
            if (sparseBooleanArray.get(i)) {
                prompt += getListView().getItemAtPosition(i).toString() + "\n";
            }
        }
        Toast.makeText(getActivity(), prompt, Toast.LENGTH_LONG).show();
    }

    public class MyListAdapter extends ArrayAdapter<String> {

        private Context context;


        public MyListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.item_for_fragmentlist, parent , false);
            TextView textView = row.findViewById(R.id.textViewName);
            textView.setText(catNames[position]);
            ImageView imageView = row.findViewById(R.id.imageViewIcon);
            imageView.setImageResource(R.drawable.ic_launcher_foreground);

            return row;

        }
    }

}
