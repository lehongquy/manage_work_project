package com.example.managerworkofstatecadres.listVehicle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerworkofstatecadres.listVehicle.licenad;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hq.manager_work.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class vehicleRecycleAdapter extends RecyclerView.Adapter<vehicleRecycleAdapter.MyViewHolder> {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference("user");
    List<licenad> slist;
    private Clicklist mclick;

    interface Clicklist {
        void onclickUpdate(licenad user);

        void onclickDelete(licenad user);
    }

    public vehicleRecycleAdapter(List<licenad> slist, Clicklist clicklist) {
        this.slist = slist;
        this.mclick = clicklist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        licenad users = slist.get(position);
        if (users == null) {
            return;
        }
        holder.textTitle.setText("Trip : " + users.getTrip());
        holder.textNametx.setText("Name : " + users.getNamedriver());

        holder.textDeparture.setText("Departure : " + users.getDeparture());
        holder.textLicense.setText("License : " + users.getLicense());
        holder.textPeople.setText("People : " + users.getPeople());
        holder.textCritical.setText("Critical:" + users.getCritical());
        holder.buttonUpdate.setOnClickListener(view -> {
            mclick.onclickUpdate(users);
        });

        holder.buttonDelete.setOnClickListener(view -> {
            mclick.onclickDelete(users);
        });

    }

    @Override
    public int getItemCount() {
        if (slist != null) {
            return slist.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textNametx, textTitle, textDeparture, textLicense, textPeople, textCritical;
        Button buttonUpdate, buttonDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            textTitle = itemView.findViewById(R.id.text_view_title);
            textNametx = itemView.findViewById(R.id.text_view_namedriver);
            textDeparture = itemView.findViewById(R.id.text_view_departure);
            textLicense = itemView.findViewById(R.id.text_view_license);
            textPeople = itemView.findViewById(R.id.text_view_people);
            textCritical = itemView.findViewById(R.id.text_view_critical);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}




