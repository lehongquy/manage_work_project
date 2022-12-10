package com.example.managerworkofstatecadres.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerworkofstatecadres.listNotification.notification;
import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.listWork.work.work;
import com.example.managerworkofstatecadres.login.screenLogin;
import com.example.managerworkofstatecadres.qr.inforMain;
import com.example.managerworkofstatecadres.qr.myViewPage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hq.manager_work.R;


public class profile extends AppCompatActivity {
    ImageView listvehicle,listwork,notification1,qr;
    TextView  reward,change,support,setting,signout, okay_text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        listvehicle = findViewById(R.id.listvehicle3);
        listwork = findViewById(R.id.listwork3);
        signout = findViewById(R.id.signout);
        notification1 = findViewById(R.id.listnotification3);
        reward= findViewById(R.id.reward);
        change= findViewById(R.id.change);
        support = findViewById(R.id.support);
        setting = findViewById(R.id.setting);
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user");
        signout.setOnClickListener(view -> {
            startActivity(new Intent(this, screenLogin.class));

        });
        reward.setOnClickListener(view -> {
            startActivity(new Intent(this,Reward.class));

        });
        change.setOnClickListener(view -> {
        Intent intent1= new Intent(this,changePass.class);
        intent1.putExtra("phone",phone);
        startActivity(intent1);
        });
        support.setOnClickListener(view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
//Thiết lập tiêu đề

            b.setMessage("Welcome to our support program because this is a demo application, so we can't fully develop the features, hope you understand.");
// Nút Ok
            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            b.show();
        });
        setting.setOnClickListener(view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
//Thiết lập tiêu đề

            b.setMessage("Welcome to our support program because this is a demo application, so we can't fully develop the features, hope you understand.");
// Nút Ok
            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            b.show();

        });
        qr = findViewById(R.id.qr3);

        myViewPage page = new myViewPage(this);
        listvehicle.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, vehicle.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        listwork.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, work.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        notification1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, notification.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        qr.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, inforMain.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
    }
}