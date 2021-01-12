package com.example.android.miwok.Activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.miwok.Fragments.NumbersFragment;
import com.example.android.miwok.R;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();
    }
}