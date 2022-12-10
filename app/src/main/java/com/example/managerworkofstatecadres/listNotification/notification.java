package com.example.managerworkofstatecadres.listNotification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerworkofstatecadres.listVehicle.vehicle;
import com.example.managerworkofstatecadres.listWork.work.work;
import com.example.managerworkofstatecadres.profile.profile;
import com.example.managerworkofstatecadres.qr.inforMain;
import com.example.managerworkofstatecadres.qr.myViewPage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;

import org.checkerframework.checker.units.qual.m;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;


public class notification extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ImageView imageView;
    ArrayList<ntObject> mlist;
    SearchView searchView;
    ImageView listvehicle,listwork,qr,profile1;
    boolean flag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.recyclerViewnt);
        imageView = findViewById(R.id.sort);
        searchView = findViewById(R.id.seachView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mlist = new ArrayList<ntObject>();
        myAdapter = new MyAdapter(mlist);

        recyclerView.setAdapter(myAdapter);
        listvehicle = findViewById(R.id.listvehicle);
        listwork = findViewById(R.id.listwork);
        profile1 = findViewById(R.id.listprofile);
        qr = findViewById(R.id.qr);
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user").child(phone);
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
        profile1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, profile.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
        qr.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, inforMain.class);
            intent1.putExtra("phone",phone);
            startActivity(intent1);
        });
    }

//        getListDatant();
//


//    private void getListDatant() {
//        databaseReference.child(phone).child("notification").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()
//                ) {
//                    ntObject object = dataSnapshot.getValue(ntObject.class);
//                    mlist.add(object);
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    @SuppressLint("NewApi")
    @Override
    protected void onStart() {
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user").child(phone).child("notification"+phone);

        super.onStart();
        Query query = databaseReference.orderByChild("critical");
        imageView.setImageResource(R.drawable.ic_baseline_swipe_down_24);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    ntObject object = dataSnapshot.getValue(ntObject.class);
                    mlist.add(object);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imageView.setOnClickListener(view -> {
            mlist.clear();
            if (flag) {
                imageView.setImageResource(R.drawable.ic_baseline_swipe_down_24);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()
                        ) {
                            ntObject object = dataSnapshot.getValue(ntObject.class);
                            mlist.add(object);
                        }
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                flag = !flag;
            } else if (!flag) {

                imageView.setImageResource(R.drawable.ic_baseline_swipe_up_24);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ntObject object = dataSnapshot.getValue(ntObject.class);
                            mlist.add(object);
                        }
                        Collections.reverse(mlist);
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                flag = !flag;
            }
        });

        if (searchView != null) {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    seach(s);
                    return true;
                }
            });
        }
    }

    private void seach(String str) {
        ArrayList<ntObject> mylist = new ArrayList<>();
        for (ntObject nt : mlist) {
            if (nt.getNament().toLowerCase().contains(str.toLowerCase())) {
                mylist.add(nt);
            }
        }
        MyAdapter myAdapter = new MyAdapter(mylist);
        recyclerView.setAdapter(myAdapter);
    }
}