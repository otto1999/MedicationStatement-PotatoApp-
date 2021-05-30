package com.example.msapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class AddMs extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private Button mBackButton;
    private Button mAddButton;

    private String id;
    private Integer position;

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

    final private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private MedicationStatement ms;
    private MedicationStatement medState;

    final private ServicesMs s = new ServicesMs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ms);

        mBackButton = findViewById(R.id.back);
        mAddButton = findViewById(R.id.add);

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




        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                id = UUID.randomUUID().toString();
                ms = new MedicationStatement(
                        id,
                        mPartOf.getText().toString(),
                        mBasedOn.getText().toString(),
                        spinner.getSelectedItem().toString(),
                        mStatusReason.getText().toString(),
                        mSubject.getText().toString(),
                        mCategory.getText().toString(),
                        mMedication.getText().toString(),
                        mInformationSource.getText().toString(),
                        mReasonCode.getText().toString(),
                        mReasonReferenc.getText().toString(),
                        mNote.getText().toString());

                Log.w("TAG",""+ms.toString());

                s.saveToFirebase(ms,v);

                Intent intent = new Intent(v.getContext(),ListMs.class);
                v.getContext().startActivity(intent);
                finish();
            }
        });
    };



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}