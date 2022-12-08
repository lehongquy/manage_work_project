package com.example.managerworkofstatecadres.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;

public class screenSignup extends AppCompatActivity {
    Button btncreate;
    EditText edtuser, edtpass, edtrepass, edtphone, edtgmail;
    TextView loginformsignup;
    ProgressBar progressBar;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_signup);
        btncreate = findViewById(R.id.id_signupaccount);
        edtuser=findViewById(R.id.id_inputname);
        edtpass = findViewById(R.id.id_inputpass);
        edtrepass = findViewById(R.id.id_inputrepass);
        edtphone = findViewById(R.id.id_inputphone);
        edtgmail = findViewById(R.id.id_inputgmail);

        loginformsignup = findViewById(R.id.id_loginsignup);
        btncreate.setOnClickListener(v -> {
            createAccount();

        });
        loginformsignup.setOnClickListener(view -> {
            finish();
        });


    }

    void createAccount() {
        String userName = edtuser.getText().toString();
        String phone = edtphone.getText().toString();
        String gmail = edtgmail.getText().toString();
        String pass = edtpass.getText().toString();
        String position ="Low-level cadres";
        String vehicle = "vehicle";
        String work = "work";
        String notification ="notification";

        String repass = edtrepass.getText().toString();
        if (userName.isEmpty() || phone.isEmpty() || gmail.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(this, "Please input inforMain", Toast.LENGTH_SHORT).show();
        }
        else if (!pass.equals(repass)) {
            Toast.makeText(this, "Please input pass and repass together", Toast.LENGTH_SHORT).show();
        }else {
            databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phone)){
                        Toast.makeText(screenSignup.this, "Phone is already", Toast.LENGTH_SHORT).show();
                    }else {
                        databaseReference.child("user").child(phone).child("fullname").setValue(userName);
                        databaseReference.child("user").child(phone).child("phone").setValue(phone);
                        databaseReference.child("user").child(phone).child("pass").setValue(pass);
                        databaseReference.child("user").child(phone).child("repass").setValue(repass);
                        databaseReference.child("user").child(phone).child("gmail").setValue(gmail);
                        databaseReference.child("user").child(phone).child("position").setValue(position);

                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("license").setValue("29D1-29854");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("trip").setValue("Bộ thông tin");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("license").setValue("29D1-28345");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("trip").setValue("Bộ nông nghiệp");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("license").setValue("29D1-28344");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("trip").setValue("Bộ quốc phòng");
                        databaseReference.child("user").child(phone).child(work).child("work1").setValue("Gặp bộ trưởng bô thông tin");
                        databaseReference.child("user").child(phone).child(work).child("work2").setValue("Gặp bộ trưởng bô nông nghiệp");
                        databaseReference.child("user").child(phone).child(work).child("work3").setValue("Gặp bộ trưởng bô quốc phòng");

                        databaseReference.child("user").child(phone).child("notification").child("notification1").setValue("họp khẩn các bộ");
                        databaseReference.child("user").child(phone).child("notification").child("notification2").setValue("họp khẩn các ban");
                        databaseReference.child("user").child(phone).child("notification").child("notification3").setValue("họp khẩn các sở");

                        startActivity(new Intent(screenSignup.this, screenLogin.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}