package com.example.managerworkofstatecadres.listWork.work.vehiclelist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.managerworkofstatecadres.listWork.work.work;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hq.manager_work.R;

public class addwork extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");
    EditText edtName,edtContext,edtTime,edtLocation,edtFloor,edtRoom,edtCritical;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwork);
         edtName = findViewById(R.id.addnameCv);
         edtContext = findViewById(R.id.addcontextCv);
         edtTime = findViewById(R.id.addtimeCv);
        edtLocation = findViewById(R.id.addlocationCv);
         edtFloor = findViewById(R.id.addFloor);
         edtRoom = findViewById(R.id.addroomCv);
        edtCritical = findViewById(R.id.addcriticalCv);
        Button btnadd = findViewById(R.id.buttonAddCv);
        Button btnCancle = findViewById(R.id.buttonCancelCv);
        btnadd.setOnClickListener(view -> {
            Createadd();
        });
        btnCancle.setOnClickListener(view -> {
            startActivity(new Intent(this, work.class));
        });

    }
    void  Createadd(){
        String Namecv = edtName.getText().toString();
        String Contextcv = edtContext.getText().toString();
        String Timecv = edtTime.getText().toString();
        String Location = edtLocation.getText().toString();
        String Floor = edtFloor.getText().toString();
        String Critical = edtCritical.getText().toString();
        String Roomcv = edtRoom.getText().toString();

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");


            databaseReference.child("user").child(phone).child("work" + phone).child( Namecv).child("namecv").setValue(Namecv);
            databaseReference.child("user").child(phone).child("work" + phone).child(Namecv).child("contextcv").setValue(Contextcv);
            databaseReference.child("user").child(phone).child("work" + phone).child(Namecv).child("timecv").setValue(Timecv);
            databaseReference.child("user").child(phone).child("work" + phone).child( Namecv).child("location").setValue(Location);
            databaseReference.child("user").child(phone).child("work" + phone).child(Namecv).child("floor").setValue(Floor);
            databaseReference.child("user").child(phone).child("work" + phone).child( Namecv).child("room").setValue(Roomcv);
            databaseReference.child("user").child(phone).child("work" + phone).child(Namecv).child("critical").setValue(Critical);
            Intent intent1 = new Intent(this, work.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
            Toast.makeText(this, "Add success", Toast.LENGTH_SHORT).show();




}}