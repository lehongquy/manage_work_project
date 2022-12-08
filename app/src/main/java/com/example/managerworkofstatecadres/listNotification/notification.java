package com.example.managerworkofstatecadres.listNotification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;


public class notification extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter ;
ArrayList<ntObject> mlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.recyclerViewnt);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mlist = new ArrayList<>();
        myAdapter = new MyAdapter(mlist);
        recyclerView.setAdapter(myAdapter);
        getListDatant();

    }
    private  void  getListDatant(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference =database.getReference("user");
            databaseReference.child("12345").child("notification").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()
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
    }
}