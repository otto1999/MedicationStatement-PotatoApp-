package com.example.msapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListMs extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView r;
    private AdapterMs adapter;
    private ArrayList<MedicationStatement> list;
    private TextView textView;
    private RadioGroup group;
    private String limit;

    final private ServicesMs s = new ServicesMs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ms);


        r = findViewById(R.id.container);
        r.setHasFixedSize(false);
        r.setLayoutManager(new LinearLayoutManager(this));

        Button buttonBack = findViewById(R.id.back);
        Button buttonAdd = findViewById(R.id.add);

        list = new ArrayList<>();
        textView = findViewById(R.id.desc);
        group = findViewById(R.id.radioGroup);

        adapter = new AdapterMs(this,list);
        r.setAdapter(adapter);

        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(1)
                .playOn(textView);

        YoYo.with(Techniques.FadeIn)
                .duration(1500)
                .repeat(0)
                .playOn(r);

        int checked = group.getCheckedRadioButtonId();
        RadioButton radioButton = group.findViewById(checked);
        limit = radioButton.getText().toString();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checked = group.getCheckedRadioButtonId();
                RadioButton radioButton = group.findViewById(checked);
                limit = radioButton.getText().toString();
                addDataToLis(choice(limit));
            }
        });



        buttonAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddMs.class);
                v.getContext().startActivity(intent);
                finish();

            }
        });

        buttonBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //A jobbra balra tolással megvalósítandó műveleteket teszi lehetővé
        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper(adapter));
        touchHelper.attachToRecyclerView(r);




    }

    @Override
    protected void onResume() {
        addDataToLis(choice(limit));
        super.onResume();
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private void search(String query) {

        ArrayList<MedicationStatement> filteredList = new ArrayList<>();

            for( MedicationStatement item : list){
                if(item.getSubject().toLowerCase().contains(query.toLowerCase())){
                    filteredList.add(item);
                }else if(item.getStatus().toLowerCase().contains(query.toLowerCase())){
                    filteredList.add(item);
                }else if(item.getMedication().toLowerCase().contains(query.toLowerCase())){
                    filteredList.add(item);
                }else {

                }
            }

            adapter = new AdapterMs(this,filteredList);
            r.setAdapter(adapter);
            adapter.notifyDataSetChanged();
    }

    public void addDataToLis(int limit){
        list.clear();
        s.getData(limit).addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document : queryDocumentSnapshots){

                MedicationStatement m = new MedicationStatement(
                        document.get("id").toString(),
                        document.get("partOf").toString(),
                        document.get("basedOn").toString(),
                        document.get("status").toString(),
                        document.get("statusReason").toString(),
                        document.get("subject").toString(),
                        document.get("category").toString(),
                        document.get("medication").toString(),
                        document.get("informationSource").toString(),
                        document.get("reasonCode").toString(),
                        document.get("reasonReferenc").toString(),
                        document.get("note").toString()
                );
                list.add(m);
            }
            adapter = new AdapterMs(this,list);
            r.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });

    }

    public int choice(String limit){
        switch(limit){
            case "5":
                return 5;
            case "10":
                return 10;
                default:
                    return 1000;
        }
    }

}