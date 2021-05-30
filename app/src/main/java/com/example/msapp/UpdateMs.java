package com.example.msapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class UpdateMs extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String PREF_KEY = UpdateMs.class.getPackage().toString();
    private SharedPreferences preferences;

    private EditText mBasedOn;
    private EditText mPartOf;
    private Spinner spinner;
    private EditText mStatusReason;
    private EditText mSubject;
    private EditText mMedication;
    private EditText mCategory;
    private EditText mReasonCode;
    private EditText mReasonReferenc;
    private EditText mInformationSource;
    private EditText mNote;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final ServicesMs s = new ServicesMs();

    MedicationStatement m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ms);

        Intent i = getIntent();
        m = (MedicationStatement) i.getSerializableExtra("Ms");

        mBasedOn = findViewById(R.id.basedOn);
        mPartOf = findViewById(R.id.partOf);
        spinner = findViewById(R.id.status);
        mStatusReason = findViewById(R.id.statusReason);
        mSubject = findViewById(R.id.subject);
        mMedication = findViewById(R.id.medication);
        mCategory = findViewById(R.id.category);
        mReasonCode = findViewById(R.id.reasonCode);
        mReasonReferenc = findViewById(R.id.reasonReferenc);
        mInformationSource  =findViewById(R.id.informationSource);
        mNote = findViewById(R.id.note);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this, R.array.status,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

    }

    public void add(View view){

        String basedOn =  mBasedOn.getText().toString();
        String partOf = mPartOf.getText().toString();
        String status = spinner.getSelectedItem().toString();
        String statusReason = mStatusReason.getText().toString();
        String subject = mSubject.getText().toString();
        String medication = mMedication.getText().toString();
        String category = mCategory.getText().toString();
        String reasonCode = mReasonCode.getText().toString();
        String reasonReferenc = mReasonReferenc.getText().toString();
        String informationSource = mInformationSource.getText().toString();
        String note = mNote.getText().toString();

        if(basedOn.matches("")){
            basedOn = m.getBasedOn();
        }
        if(partOf.matches("")) {
            partOf = m.getPartOf();
        }
        if(status.matches("Aktív")) {
            status = m.getStatus();
        }
        if(statusReason.matches("")){
            statusReason = m.getStatusReason();
        }
        if(subject.matches("")){
            subject = m.getSubject();
        }
        if(medication.matches("")){
            medication = m.getMedication();
        }
        if(category.matches("")){
            category = m.getCategory();
        }
        if(reasonCode.matches("")){
            reasonCode = m.getReasonCode();
        }
        if(reasonReferenc.matches("")){
            reasonReferenc = m.getReasonReference();
        }
        if(informationSource.matches("")){
            informationSource = m.getInformationSource();
        }
        if(note.matches("")){
            note = m.getNote();
        }

        MedicationStatement ms  = new MedicationStatement(m.getId(),partOf,basedOn,status,statusReason,subject,category,medication,informationSource,reasonCode,reasonReferenc,note);

        s.updateData(ms,view);
        finish();

    }
    public void back(View view){
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}