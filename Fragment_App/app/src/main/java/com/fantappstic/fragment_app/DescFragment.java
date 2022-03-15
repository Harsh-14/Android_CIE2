package com.fantappstic.fragment_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescFragment extends Fragment {
    TextView textView;
    String str;

    public DescFragment(String desc) {
        this.str=desc;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_desc, container, false);
       textView=view.findViewById(R.id.textView2);
       textView.setText(str);
        return view;
    }
}