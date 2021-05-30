package com.example.msapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class DetailsMs extends AppCompatActivity {

    private static final String PREF_KEY = DetailsMs.class.getPackage().toString();
    private SharedPreferences preferences;

    private TextView basedOn;
    private TextView partOf;
    private TextView status;
    private TextView statusReason;
    private TextView subject;
    private TextView medication;
    private TextView category;
    private TextView informationSource;
    private TextView reasonCode;
    private TextView reasonReferenc;
    private TextView note;

    private Button buttonBack;

    private AdapterMs adapter;
    private MedicationStatement m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ms);

        basedOn = findViewById(R.id.basedOnText);
        partOf = findViewById(R.id.partOfText);
        status = findViewById(R.id.statusText);
        statusReason = findViewById(R.id.statusReasonText);
        subject = findViewById(R.id.subjectText);
        medication = findViewById(R.id.medicationText);
        category = findViewById(R.id.categoryText);
        informationSource = findViewById(R.id.informationSourceText);
        reasonCode = findViewById(R.id.reasonCodeText);
        reasonReferenc = findViewById(R.id.reasonReferenceText);
        note = findViewById(R.id.noteText);

        buttonBack = findViewById(R.id.back);

        Intent i = getIntent();

         m = (MedicationStatement) i.getSerializableExtra("Ms");
        adapter = new AdapterMs(DetailsMs.this,m);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

            basedOn.setText(m.getBasedOn());
            partOf.setText(m.getPartOf());
            status.setText(m.getStatus());
            statusReason.setText(m.getStatusReason());
            subject.setText(m.getSubject());
            medication.setText(m.getMedication());
            category.setText(m.getCategory());
            informationSource.setText(m.getCategory());
            reasonCode.setText(m.getReasonCode());
            reasonReferenc.setText(m.getReasonReference());
            note.setText(m.getNote());
    }

    public void back(View view) {
        finish();
    }

    public void update(View view){
        Intent intent = new Intent(view.getContext(),UpdateMs.class);
        intent.putExtra("Ms",(Serializable) m);
        view.getContext().startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }



}