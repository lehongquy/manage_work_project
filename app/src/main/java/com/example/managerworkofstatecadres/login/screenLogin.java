package com.example.managerworkofstatecadres.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.managerworkofstatecadres.listNotification.notification;
import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.listWork.work.work;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;

public class screenLogin extends AppCompatActivity {
    Button btnLogin;
    TextView tvsignup;
    EditText edtphone ,edtpass;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com");
    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
        btnLogin=findViewById(R.id.id_login);
        tvsignup=findViewById(R.id.id_signup);
        edtphone = findViewById(R.id.id_inputuser);
        edtpass = findViewById(R.id.id_inputpass);
        btnLogin.setOnClickListener(v -> {
            final String phone =edtphone.getText().toString();
            final String pass =edtpass.getText().toString();
            if (phone.isEmpty()||pass.isEmpty()){
                Toast.makeText(this, "Please,Input pass or phone", Toast.LENGTH_SHORT).show();
            }else{
                databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phone)){
                            final  String getPass = snapshot.child(phone).child("pass").getValue(String.class);
                            if (getPass.equals(pass)){
                                Toast.makeText(screenLogin.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(screenLogin.this, vehicle.class);
                                intent.putExtra("phone", phone);
                                startActivity(intent);

                                finish();
                            }else {
                                Toast.makeText(screenLogin.this, "Input pass fail", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(screenLogin.this, "Input phone fail", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        tvsignup.setOnClickListener(v -> {
            startActivity(new Intent(screenLogin.this, screenSignup.class));
        });
    }
}