package com.example.managerworkofstatecadres.listVehicle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerworkofstatecadres.listNotification.notification;
import com.example.managerworkofstatecadres.listVehicle.vehiclelist.addvehicle;
import com.example.managerworkofstatecadres.listWork.work.work;
import com.example.managerworkofstatecadres.profile.profile;
import com.example.managerworkofstatecadres.qr.inforMain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;

import java.util.ArrayList;
import java.util.Collections;


public class vehicle extends AppCompatActivity {
    RecyclerView recyclerView;
    vehicleRecycleAdapter adapter;
    ImageView imageView, listwork, qr1, notification1, profile1;
    ArrayList<licenad> mlist = new ArrayList<licenad>();
    ;
    FloatingActionButton btnadd;

    boolean flag = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        recyclerView = findViewById(R.id.recycviewvh);
        imageView = findViewById(R.id.sort1);
        listwork = findViewById(R.id.listwork);
        qr1 = findViewById(R.id.qr);
        notification1 = findViewById(R.id.listnotification);
        profile1 = findViewById(R.id.listprofile);
        btnadd = findViewById(R.id.btnaddvehicle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new vehicleRecycleAdapter(mlist, new vehicleRecycleAdapter.Clicklist() {
            @Override
            public void onclickUpdate(licenad user) {
                Intent intent = getIntent();
                String phone = intent.getStringExtra("phone");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("user").child(phone).child("vehicle" + phone);
                databaseReference.child(String.valueOf(user.getTrip())).removeValue((error, ref) -> {

                });

                openDialogUpdate(user);
            }

            @Override
            public void onclickDelete(licenad user) {
                openDialogDelete(user);
            }
        });

        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user").child(phone).child("vehicle" + phone);
        listwork.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, work.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
        });
        qr1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, inforMain.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
        });
        notification1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, notification.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
        });
        profile1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, profile.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
        });

        btnadd.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, addvehicle.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
        });

    }

//    private void getListDatant() {
//        Intent intent = getIntent();
//        String phone = intent.getStringExtra("phone");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference("user").child(phone).child("vehicle"+phone);
//
//        databaseReference.child(phone).child("notification").addChildEventListener(new ChildEventListener() {
//        });
//    }


    @Override
    protected void onStart() {
        super.onStart();
        mlist.clear();
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user").child(phone).child("vehicle" + phone);


        Query query = databaseReference.orderByChild("critical");
        imageView.setImageResource(R.drawable.ic_baseline_swipe_down_24);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {

                    licenad object = dataSnapshot.getValue(licenad.class);

                    mlist.add(object);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imageView.setOnClickListener(view -> {
            mlist.clear();
            if (flag) {
                imageView.setImageResource(R.drawable.ic_baseline_swipe_down_24);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()
                        ) {
                            licenad object = dataSnapshot.getValue(licenad.class);
                            mlist.add(object);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                flag = !flag;
            } else if (!flag) {

                imageView.setImageResource(R.drawable.ic_baseline_swipe_up_24);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            licenad object = dataSnapshot.getValue(licenad.class);
                            mlist.add(object);
                        }
                        Collections.reverse(mlist);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                flag = !flag;
            }
        });


    }


    private void openDialogDelete(licenad user) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Do you want  delete note?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        String phone = intent.getStringExtra("phone");
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = database.getReference("user").child(phone).child("vehicle" + phone);
                        databaseReference.child(String.valueOf(user.getTrip())).removeValue((error, ref) -> {
                            Toast.makeText(vehicle.this, "Delete Success", Toast.LENGTH_SHORT).show();
                        });
                    }
                }).setNegativeButton("Cancle", null).show();

    }

    private void openDialogUpdate(licenad user) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.updatevehicle);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        EditText edtName = dialog.findViewById(R.id.nameDr);
        EditText edtTrip = dialog.findViewById(R.id.Tripvc);
        EditText edtDeparture = dialog.findViewById(R.id.Departurevc);
        EditText edtLicense = dialog.findViewById(R.id.Licensevc);
        EditText edtPeople = dialog.findViewById(R.id.Peoplevc);
        EditText edtCritical = dialog.findViewById(R.id.Criticalvc);
        Button btnUp = dialog.findViewById(R.id.btnUpdatevc);
        Button btnCancle = dialog.findViewById(R.id.btnExitvc);
        btnCancle.setOnClickListener(view -> {
            dialog.dismiss();
        });
        btnUp.setOnClickListener(view -> {
            Intent intent = getIntent();
            String phone = intent.getStringExtra("phone");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("user").child(phone).child("vehicle" + phone);
            String newName = edtName.getText().toString().trim();
            String newTrip = edtTrip.getText().toString().trim();
            String newDeparture = edtDeparture.getText().toString().trim();
            String newLicense = edtLicense.getText().toString().trim();
            String newPeople = edtPeople.getText().toString().trim();
            String newCritical = edtCritical.getText().toString().trim();
            user.setNamedriver(newName);
            user.setCritical(newCritical);
            user.setDeparture(newDeparture);
            user.setLicense(newLicense);
            user.setPeople(newPeople);
            user.setTrip(newTrip);

            databaseReference.child(user.getTrip()).updateChildren(user.toMap(), (error, ref) -> {
                Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });

        });
        dialog.show();
    }

}