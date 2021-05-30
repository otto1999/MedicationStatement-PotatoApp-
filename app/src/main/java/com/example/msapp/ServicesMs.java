package com.example.msapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicesMs {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<MedicationStatement> list = new ArrayList<>();
    private RecyclerView r;

    public void saveToFirebase(MedicationStatement ms , View v) {

        if (ms.getSubject().length() == 0 || ms.getMedication().length() == 0 ) {
            Toast.makeText(v.getContext(),"A gyógyszer és az alany nevének megadása kötelező" + ms.getSubject(),Toast.LENGTH_LONG).show();
        } else {

            Log.w("TAG" , ""+ms.toString()+" ");

            Map<String, Object> map = new HashMap<>();
            map.put("id", ms.getId());
            map.put("basedOn", ms.getBasedOn());
            map.put("partOf", ms.getPartOf());
            map.put("status", ms.getStatus());
            map.put("statusReason", ms.getStatusReason());
            map.put("subject", ms.getSubject());
            map.put("medication", ms.getMedication());
            map.put("category", ms.getCategory());
            map.put("reasonCode", ms.getReasonCode());
            map.put("reasonReferenc", ms.getReasonReference());
            map.put("informationSource", ms.getInformationSource());
            map.put("note", ms.getNote());

            db.collection("Medications").document(ms.getId()).set(map)
                    .addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            Toast.makeText(v.getContext(), "Sikeres feltöltés", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(v.getContext(), "Sikertelen feltöltés", Toast.LENGTH_SHORT).show();
                        }
                    });


        }
    }

    public Task<QuerySnapshot> getData(int limit) {
        if(limit < 15){
            return db.collection("Medications").limit(limit).orderBy("subject").get();
        }else{
            return db.collection("Medications").orderBy("subject").get();
        }
    }


    public void updateData(MedicationStatement ms, View v){
        Log.w("TAG",ms.toString());
        db.collection("Medications").document(ms.getId()).update(
                "partOf",ms.getPartOf(),
                "basedOn",ms.getBasedOn(),
                "status",ms.getStatus(),
                "statusReason",ms.getStatusReason(),
                "subject",ms.getSubject(),
                "medication", ms.getMedication(),
                "category", ms.getCategory(),
                "reasonCode", ms.getReasonCode(),
                "reasonReferenc", ms.getReasonReference(),
                "informationSurce", ms.getInformationSource(),
                "note",ms.getNote())
                .addOnSuccessListener( new OnSuccessListener() {

                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(v.getContext(),"Sikeres frissítés",Toast.LENGTH_SHORT);
                    }
                })
                .addOnFailureListener( new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(),"Sikertelen a frissítés",Toast.LENGTH_SHORT);
                    }
                });
    }

    public void deleteData(String id){
        db.collection("Medications").document(id).delete();
    }

}
