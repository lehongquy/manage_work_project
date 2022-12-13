package com.example.managerworkofstatecadres.qr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.example.managerworkofstatecadres.listNotification.notification;
import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.listWork.work.work;
import com.example.managerworkofstatecadres.profile.pro;
import com.example.managerworkofstatecadres.profile.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;


public class inforMain extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView view;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        viewPager2 = findViewById(R.id.viewpage2);
        view = findViewById(R.id.bottomNv);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user").child(phone);
        myViewPage page = new myViewPage(this);
        bottomNavigationView = findViewById(R.id.bnView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnnotification:
                        Intent intent = new Intent(inforMain.this, notification.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        return true;
                    case R.id.bnwork:
                        Intent intent1 = new Intent(inforMain.this, work.class);
                        intent1.putExtra("phone", phone);
                        startActivity(intent1);
                        return true;
                    case R.id.bnprofile:
                        Intent intent2 = new Intent(inforMain.this, profile.class);
                        intent2.putExtra("phone", phone);
                        startActivity(intent2);
                        return true;
                    case R.id.bnvehicel:
                        Intent intent3 = new Intent(inforMain.this, vehicle.class);
                        intent3.putExtra("phone", phone);
                        startActivity(intent3);
                        return true;

                }
                return false;
            }
        });


        Read();

        viewPager2.setAdapter(page);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        view.getMenu().findItem(R.id.id_qrcode).setChecked(true);

                        break
                                ;
                    case 1:
                        view.getMenu().findItem(R.id.id_guest).setChecked(true);

                        break
                                ;

                }
            }
        });
        view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.id_qrcode:
                    viewPager2.setCurrentItem(0);
                    break;
                case R.id.id_guest:
                    viewPager2.setCurrentItem(1);
                    break;
            }
            return true;
        });
    }

    String infor1 = "";

    void Read() {
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");
        reference.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            @SuppressLint("NewApi")
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pro infor = snapshot.getValue(pro.class);
                infor1 = infor.gettoString();


//                byte[] decodeString = Base64.getDecoder().decode(infor.getImage());
//                Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                im.setImageBitmap(decodeByte);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}