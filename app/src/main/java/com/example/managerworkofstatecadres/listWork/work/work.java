package com.example.managerworkofstatecadres.listWork.work;

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
import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.listWork.work.vehiclelist.addwork;
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


public class work extends AppCompatActivity {
    RecyclerView recyclerView;
    workRecycleAdapter adapter;
    ImageView imageView,listvehicle,qr1,notification1,profile1;
    ArrayList<workOject> mlist;
    FloatingActionButton btnadd;

    boolean flag = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        recyclerView = findViewById(R.id.recycviewwk);
        imageView = findViewById(R.id.sort2);
        listvehicle = findViewById(R.id.listvehicle2);
        qr1 = findViewById(R.id.qr2);
        notification1 = findViewById(R.id.listnotification2);
        profile1 = findViewById(R.id.listprofile2);
        btnadd = findViewById(R.id.btnaddwork);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mlist = new ArrayList<workOject>();
        adapter = new workRecycleAdapter(mlist, new workRecycleAdapter.Clicklist1() {
            @Override
            public void onclickUpdate(workOject user) {

                Intent intent = getIntent();
                String phone = intent.getStringExtra("phone");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("user").child(phone).child("work" + phone);
                databaseReference.child(String.valueOf(user.getNamecv())).removeValue((error, ref) -> {
                    Toast.makeText(work.this, "Delete Success", Toast.LENGTH_SHORT).show();
                });
                openDialogUpdate(user);
            }

            @Override
            public void onclickDelete(workOject user) {
                openDialogDelete(user);
            }
        });

        recyclerView.setAdapter(adapter);


    }

//    private void getListDatant() {
//        databaseReference.child(phone).child("notification").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()
//                ) {
//                    workOject object = dataSnapshot.getValue(workOject.class);
//                    mlist.add(object);
//                }
//                vehicleRecycleAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });  lequy1
//    }


    @Override
    protected void onStart() {
        super.onStart();
        mlist.clear();
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user").child(phone).child("work"+phone);
        btnadd.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, addwork.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        listvehicle.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, vehicle.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        qr1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, inforMain.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        notification1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, notification.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        profile1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, profile.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        super.onStart();
        Query query = databaseReference.orderByChild("critical");
        imageView.setImageResource(R.drawable.ic_baseline_swipe_down_24);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    workOject object = dataSnapshot.getValue(workOject.class);
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
                            workOject object = dataSnapshot.getValue(workOject.class);
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
                            workOject object = dataSnapshot.getValue(workOject.class);
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

    private  void openDialogDelete(workOject user){
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Do you want  delete note?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        String phone = intent.getStringExtra("phone");
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = database.getReference("user").child(phone).child("work"+phone);
                        databaseReference.child(String.valueOf(user.getNamecv())).removeValue((error, ref) -> {
                            Toast.makeText(work.this, "Delete Success", Toast.LENGTH_SHORT).show();
                        });
                    }
                }).setNegativeButton("Cancle",null).show();

    }

    private  void openDialogUpdate(workOject user) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.updatework);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        EditText edtName = dialog.findViewById(R.id.nameWk);
        EditText edtContext = dialog.findViewById(R.id.contextWk);
        EditText edtTime = dialog.findViewById(R.id.timeWk);
        EditText edtFloor = dialog.findViewById(R.id.floorWk);
        EditText edtRoom = dialog.findViewById(R.id.roomWk);
        EditText edtLocation = dialog.findViewById(R.id.locationWk);
        EditText edtCritical = dialog.findViewById(R.id.criticalWk);

        Button btnUp = dialog.findViewById(R.id.btnUpdateWk);
        Button btnCancle = dialog.findViewById(R.id.btnExitWk);
        btnCancle.setOnClickListener(view -> {
            dialog.dismiss();
        });
        btnUp.setOnClickListener(view -> {
            Intent intent = getIntent();
            String phone = intent.getStringExtra("phone");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("user").child(phone).child("work"+phone);
            String  newName = edtName.getText().toString().trim();
            String  newContext = edtContext.getText().toString().trim();
            String  newTime = edtTime.getText().toString().trim();
            String  newLocation = edtLocation.getText().toString().trim();
            String  newFloor = edtFloor.getText().toString().trim();
            String  newRoom = edtRoom.getText().toString().trim();
            String  newCritical = edtCritical.getText().toString().trim();
            user.setNamecv(newName);
            user.setCritical(newCritical);
            user.setContextcv(newContext);
            user.setTimecv(newTime);
            user.setFloor(newFloor);
            user.setRoom(newRoom);
            user.setLocation(newLocation);
            databaseReference.child(String.valueOf(user.getNamecv())).updateChildren(user.toMap(),(error, ref) ->{
                Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } );
        });
        dialog.show();
    }

}