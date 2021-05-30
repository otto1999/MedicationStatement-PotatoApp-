package com.example.msapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class AdapterMs extends RecyclerView.Adapter<AdapterMs.ViewHolderMs  > {

    ArrayList<MedicationStatement> mList;
    MedicationStatement ms;
    Context context;
    ServicesMs s;

    public AdapterMs( Context context,ArrayList<MedicationStatement> mList){
        this.mList = mList;
        this.context = context;
        this.s = new ServicesMs();
    }
    public AdapterMs( Context context,MedicationStatement ms){
        this.ms = ms;
        this.context = context;
        this.s = new ServicesMs();
    }

    public void setmList(ArrayList<MedicationStatement> mList) {
        this.mList = mList;
    }

    public void toDetails(int position){
        Log.i("SIKERES","SIKERES BALRAHÚZÁSR");
        MedicationStatement m = mList.get(position);
        Log.i("SIKERES LEKÉRÉS","" + m.toString());
        Intent intent = new Intent(context,DetailsMs.class );
        intent.putExtra("Ms",(Serializable) m);
        context.startActivity(intent);

    }

    public void update(){
        Intent intent = new Intent(context,UpdateMs.class);
        intent.putExtra("ms", (Serializable) ms);
        Log.w("TAG"," EZ LEFUT!");
        context.startActivity(intent);
    }

    public void delete(int position){
        MedicationStatement m = mList.get(position);
        s.deleteData(m.getId());
        notifyDelete(position);
    }
    public void notifyDelete(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolderMs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolderMs(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMs holder, int position) {
        MedicationStatement m = mList.get(position);
        holder.sub.setText(m.getSubject());
        holder.med.setText(m.getMedication());
        holder.stat.setText(m.getStatus());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  static class ViewHolderMs extends  RecyclerView.ViewHolder{

        TextView sub , med , stat ;

        public ViewHolderMs(@NonNull View itemView) {
            super(itemView);

            sub =  itemView.findViewById(R.id.subject);
            med = itemView.findViewById(R.id.medication);
            stat = itemView.findViewById(R.id.status);
        }
    }
}
