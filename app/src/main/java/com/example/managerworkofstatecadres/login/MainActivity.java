package com.example.managerworkofstatecadres.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import com.example.managerworkofstatecadres.qr.inforMain;
import com.hq.manager_work.R;

public class MainActivity extends AppCompatActivity {
Button btn ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, inforMain.class));
        });
    }
}