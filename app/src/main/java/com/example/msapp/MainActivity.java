package com.example.msapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listMs(View view) {
        Intent intent = new Intent(this, ListMs.class);
        intent.putExtra("KEY",99);
        startActivity(intent);
    }

    public void addMs(View view) {
        Intent intent = new Intent(this, AddMs.class);
        startActivity(intent);
    }
}