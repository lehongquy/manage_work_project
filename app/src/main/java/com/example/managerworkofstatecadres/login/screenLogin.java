package com.example.managerworkofstatecadres.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.managerworkofstatecadres.OTP.OtpSendActivity;
import com.example.managerworkofstatecadres.listNotification.notification;

import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.listWork.work.work;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;

public class screenLogin extends AppCompatActivity {
    Button btnLogin;
    TextView tvsignup, tvforgot;
    EditText edtphone, edtpass;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com");
    private final int RECORD = 1;

    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
        btnLogin = findViewById(R.id.id_login);
        tvsignup = findViewById(R.id.id_signup);
        edtphone = findViewById(R.id.id_inputuser);
        edtpass = findViewById(R.id.id_inputpass);
        tvforgot = findViewById(R.id.id_forgot);
        int permisstion_write_storage = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permisstion_read_storage = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permisstion_camera = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        int permisstion_sendsms = ContextCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS);
        if (permisstion_camera != PackageManager.PERMISSION_GRANTED || permisstion_read_storage != PackageManager.PERMISSION_GRANTED || permisstion_sendsms != PackageManager.PERMISSION_GRANTED || permisstion_write_storage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.VIBRATE, Manifest.permission.READ_EXTERNAL_STORAGE}, RECORD);
        }
        tvforgot.setOnClickListener(v -> {
            startActivity(new Intent(this, OtpSendActivity.class));
        });
        btnLogin.setOnClickListener(v -> {
            String phone = edtphone.getText().toString();
            String pass = edtpass.getText().toString();
            if (phone.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please,Input pass or phone", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phone)) {
                            String getPass = snapshot.child(phone).child("pass").getValue(String.class);
                            if (getPass.equals(pass)) {
                                Toast.makeText(screenLogin.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(screenLogin.this, work.class);// Truyền một Boolean
                                intent.putExtra("phone", phone);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(screenLogin.this, "Input pass fail", Toast.LENGTH_SHORT).show();
                            }
                        } else {
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