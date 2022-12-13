package com.example.managerworkofstatecadres.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.managerworkofstatecadres.listNotification.notification;
import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.listWork.work.work;
import com.example.managerworkofstatecadres.login.screenLogin;
import com.example.managerworkofstatecadres.qr.inforGuest;
import com.example.managerworkofstatecadres.qr.inforMain;
import com.example.managerworkofstatecadres.qr.myViewPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


public class profile extends AppCompatActivity {
    ImageView imagepro;
    TextView reward, change, support, setting, signout, phonepro, namepro, gmailpro, position;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com");
    FloatingActionButton btnFloat;
    BottomNavigationView bottomNavigationView;

    private static final int PICK_IMAGE_REQUEST = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        phonepro = findViewById(R.id.phonepro);
        gmailpro = findViewById(R.id.gmailpro);
        position = findViewById(R.id.positionpro);
        namepro = findViewById(R.id.namepro);
        signout = findViewById(R.id.signout);

        reward = findViewById(R.id.reward);
        change = findViewById(R.id.change);
        support = findViewById(R.id.support);
        setting = findViewById(R.id.setting);
        imagepro = findViewById(R.id.imagepro);
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user").child(phone);
        Read();
        signout.setOnClickListener(view -> {
            startActivity(new Intent(this, screenLogin.class));

        });
        reward.setOnClickListener(view -> {
            startActivity(new Intent(this, Reward.class));

        });
        change.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, changePass.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
        });
        support.setOnClickListener(view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
//Thiết lập tiêu đề

            b.setMessage("Welcome to our support program because this is a demo application, so we can't fully develop the features, hope you understand.");
// Nút Ok
            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

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

                }
            });
            b.show();

        });


        myViewPage page = new myViewPage(this);


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        String read = Read().toString();
//        Toast.makeText(this, read, Toast.LENGTH_SHORT).show();
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//
//
//
//            Toast.makeText(this, read, Toast.LENGTH_SHORT).show();
//        }
//    }
////

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");
        bottomNavigationView = findViewById(R.id.bnView);
        btnFloat = findViewById(R.id.floatbtn);
        bottomNavigationView.setSelectedItemId(R.id.bnprofile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnnotification:
                        Intent intent2 = new Intent(profile.this, notification.class);
                        intent2.putExtra("phone", phone);
                        startActivity(intent2);
                        return true;
                    case R.id.bnwork:
                        Intent intent1 = new Intent(profile.this, work.class);
                        intent1.putExtra("phone", phone);
                        startActivity(intent1);
                        return true;
                    case R.id.bnprofile:

                        return true;
                    case R.id.bnvehicel:
                        Intent intent3 = new Intent(profile.this, vehicle.class);
                        intent3.putExtra("phone", phone);
                        startActivity(intent3);
                        return true;

                }
                return false;
            }
        });

        btnFloat.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, inforMain.class);
            intent1.putExtra("phone", phone);
            startActivity(intent1);
        });

    }

    @SuppressLint({"NewApi", "LocalSuppress"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {


        }
    }

    String img;

    private void Read() {
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");
        reference.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            @SuppressLint("NewApi")
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pro infor = snapshot.getValue(pro.class);
                phonepro.setText(infor.getPhone());
                namepro.setText(infor.getFullname());
                gmailpro.setText(infor.getGmail());
                position.setText(infor.getPosition());

//                byte[] decodeString = Base64.getDecoder().decode(infor.getImage());
//                Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                im.setImageBitmap(decodeByte);
                bytesToImage(imagepro, infor.getImage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    static byte[] getByteUri(Context context, Uri uri) {
        InputStream inputStream = null;

        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int buffet = 1024;
            byte[] buf = new byte[buffet];
            int leng = 0;
            while ((leng = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, leng);
            }
            return outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private void bytesToImage(ImageView imageView, String base64String) {
        if (!base64String.isEmpty()) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(base64String);
            }
            Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            Glide.with(this).load(decodedByte).into(imageView);
        }
    }
}