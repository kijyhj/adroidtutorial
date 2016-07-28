package com.example.a.a24_flagment2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountFragment extends Fragment {


    public CountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_count, container, false);
        final TextView countTextView = (TextView) view.findViewById(R.id.countTextView);
        Button btnIncrease = (Button) view.findViewById(R.id.increase);

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = (String) countTextView.getText();
                int number = Integer.parseInt(str);

                number++;

                countTextView.setText("" + number);
            }
        });

        return view;
    }

}
