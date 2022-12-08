package com.example.managerworkofstatecadres.listWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import com.example.managerworkofstatecadres.listWork.work.work;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;


public class createWork extends AppCompatActivity {
    Button btncreate;
    EditText edtname, edttime, edtlocation, edtfloor, edtroom;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_signup);
        btncreate = findViewById(R.id.id_signupaccount);
        edtname=findViewById(R.id.id_inputname);
        edttime = findViewById(R.id.id_inputtime);
        edtlocation = findViewById(R.id.id_inputlocation);
        edtfloor = findViewById(R.id.id_inputfloor);
        edtroom = findViewById(R.id.id_inputroom);


        btncreate.setOnClickListener(v -> {
            createAccount();

        });



    }

    void createAccount() {
        String namecv = edtname.getText().toString();
        String time = edttime.getText().toString();
        String location = edtlocation.getText().toString();
        String floor = edtfloor.getText().toString();
        String room = edtfloor.getText().toString();

            databaseReference.child("vehicle").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                        databaseReference.child("vihicle").child(namecv).child("namecv").setValue(namecv);
                        databaseReference.child("vihicle").child(namecv).child("time").setValue(time);
                        databaseReference.child("vihicle").child(namecv).child("location").setValue(location);
                        databaseReference.child("vihicle").child(namecv).child("floor").setValue(floor);
                        databaseReference.child("vihicle").child(namecv).child("room").setValue(room);
                        startActivity(new Intent(createWork.this, work.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
