package com.example.managerworkofstatecadres.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerworkofstatecadres.login.screenLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;


public class changePass extends AppCompatActivity {

    EditText editPassold, editPassword, editRePassWord;
    Button saveButton;
    String usernameUser, passwordUser, repasswordUser;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editPassold = findViewById(R.id.editPasswordold);
        editPassword = findViewById(R.id.editPassword);
        editRePassWord = findViewById(R.id.editRePassword);
        saveButton = findViewById(R.id.saveButton);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordChanged()) {

                    Toast.makeText(changePass.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(changePass.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public boolean isPasswordChanged(){
        Intent intent  = getIntent();
        String phone= intent.getStringExtra("phone");
        String passnew = editPassword.getText().toString();
        String passRenew = editRePassWord.getText().toString();
        String passold = editPassold.getText().toString();
        if (passRenew.isEmpty() || passold.isEmpty()|| passnew.isEmpty()) {
            Toast.makeText(this, "Please,Input pass ", Toast.LENGTH_SHORT).show();
        } else {
            reference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String getPass = snapshot.child(phone).child("pass").getValue(String.class);
                    if (getPass.equals(passold)) {
                        if (passnew.equals(passRenew)) {
                            reference.child("user").child(phone).setValue(passnew);
                            Toast.makeText(changePass.this, "Successfully change", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(changePass.this, screenLogin.class);// Truyền một Boolean
                            intent.putExtra("phone", phone);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(changePass.this, "Input repass or newpass don't together", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(changePass.this, "Input pass fail", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return false;
    }}