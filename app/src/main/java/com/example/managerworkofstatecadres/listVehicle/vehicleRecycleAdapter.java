package com.example.managerworkofstatecadres.listVehicle;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hq.manager_work.R;

import java.util.ArrayList;

public class vehicleRecycleAdapter extends RecyclerView.Adapter<vehicleRecycleAdapter.ViewHolder> {

    Context context;
    ArrayList<licenad> usersItemArrayList;
    DatabaseReference databaseReference;

    public vehicleRecycleAdapter(Context context, ArrayList<licenad> usersItemArrayList) {
        this.context = context;
        this.usersItemArrayList = usersItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_vehicle, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        licenad users = usersItemArrayList.get(position);

        holder.textNametx.setText("Name : " + users.getNametx());
        holder.textTitle.setText("Trip : " + users.getTrip());
        holder.textDeparture.setText("Departure : " + users.getDeparture());
        holder.textLicense.setText("License : " + users.getLicense());
        holder.textPeople.setText("People : " + users.getPeople());

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, users.getTrip(), users.getNametx(), users.getDeparture(), users.getLicense(),users.getPeople());
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, users.getTrip());
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNametx;
        TextView textTitle;
        TextView textDeparture;
        TextView textLicense;
        TextView textPeople;

        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNametx = itemView.findViewById(R.id.text_view_namedriver);
            textTitle = itemView.findViewById(R.id.text_view_title);
            textDeparture = itemView.findViewById(R.id.text_view_departure);
            textLicense = itemView.findViewById(R.id.text_view_license);
            textPeople = itemView.findViewById(R.id.text_view_people);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String title, String nametx, String departure,  String license,String people ) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_vehicle);

            EditText textNametx = dialog.findViewById(R.id.nameTx);
            EditText textTitle = dialog.findViewById(R.id.textTrip);
            EditText textDeparture = dialog.findViewById(R.id.textDeparture);
            EditText textLicense = dialog.findViewById(R.id.textLicense);
            EditText textPeople = dialog.findViewById(R.id.textPeople);

            textNametx.setText(nametx);
            textTitle.setText(title);
            textDeparture.setText(departure);
            textLicense.setText(license);
            textPeople.setText(people);

            Button buttonUpdate = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("UPDATE");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newNametx = textNametx.getText().toString();
                    String newTitle = textTitle.getText().toString();
                    String newDeparture = textDeparture.getText().toString();
                    String newLicense = textLicense.getText().toString();
                    String newPeople = textPeople.getText().toString();


                    if (nametx.isEmpty() || title.isEmpty() || departure.isEmpty()||license.isEmpty()||people.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newNametx.equals(nametx) && newTitle.equals(title) && newDeparture.equals(departure)&&newLicense.equals(license)&&newPeople.equals(people)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("user").child(title).setValue(new licenad(newNametx, newLicense, newTitle, newDeparture,newPeople));
                            Toast.makeText(context, "User Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }


    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete_vehicle);

            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    databaseReference.child("user").child(id).removeValue();
                    Toast.makeText(context, "User Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
}
