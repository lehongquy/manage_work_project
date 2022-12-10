package com.example.managerworkofstatecadres.listVehicle.vehiclelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.login.screenLogin;
import com.example.managerworkofstatecadres.login.screenSignup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;

public class addvehicle extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");

    EditText edtName,edtTrip,edtDeparture,edtLicense,edtPeople,edtCritical;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvehicle);

         edtName = findViewById(R.id.addnameTx);
        edtTrip = findViewById(R.id.addTrip);
       edtDeparture = findViewById(R.id.addDeparture);
       edtLicense = findViewById(R.id.addLicense);
        edtPeople = findViewById(R.id.addPeople);
       edtCritical = findViewById(R.id.addCritical);

        Button btnadd = findViewById(R.id.buttonAddvh);
        Button btnCancle = findViewById(R.id.buttonCancelvh);  btnadd.setOnClickListener(view -> {
            Createvihelce();
        });
        btnCancle.setOnClickListener(view -> {
            startActivity(new Intent(this, vehicle.class));
        });
    }
void Createvihelce(){
    Intent intent = getIntent();
    String phone = intent.getStringExtra("phone");
    String Namedr= edtName.getText().toString();
    String Tripvh= edtTrip.getText().toString();
    String Licensevh= edtLicense.getText().toString();
    String Departure= edtDeparture.getText().toString();
    String People= edtPeople.getText().toString();
    String Critical= edtCritical.getText().toString();

        databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(Tripvh)){
                    Toast.makeText(addvehicle.this, "Trip is already", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("user").child(phone).child("vehicle"+phone).child(Tripvh).child("license").setValue(Licensevh);
                    databaseReference.child("user").child(phone).child("vehicle"+phone).child(Tripvh).child("trip").setValue(Tripvh);
                    databaseReference.child("user").child(phone).child("vehicle"+phone).child(Tripvh).child("namedriver").setValue(Namedr);
                    databaseReference.child("user").child(phone).child("vehicle"+phone).child(Tripvh).child("departure").setValue(Departure);
                    databaseReference.child("user").child(phone).child("vehicle"+phone).child(Tripvh).child("people").setValue(People);
                    databaseReference.child("user").child(phone).child("vehicle"+phone).child(Tripvh).child("critical").setValue(Critical);
                    Toast.makeText(addvehicle.this, "Add success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addvehicle.this, vehicle.class));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





}