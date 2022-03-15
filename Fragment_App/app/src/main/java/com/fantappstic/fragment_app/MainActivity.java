package com.fantappstic.fragment_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fr;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fr=getSupportFragmentManager();
        ft=fr.beginTransaction();
        ft.add(R.id.wrapper,new ImageFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}