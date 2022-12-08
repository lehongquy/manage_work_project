package com.example.managerworkofstatecadres.listWork.work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.hq.manager_work.R;


public class work extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Intent intent = getIntent();
        String phone=intent.getStringExtra("phone");

        Query query = FirebaseDatabase.getInstance().getReference().child("user").child(phone).child("work");
        FirebaseListOptions<workOject> options = new FirebaseListOptions.Builder<workOject>().setLayout(R.layout.singlerowdesign).setQuery(query,workOject.class).build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView namecv = findViewById(R.id.nametext);
                TextView contextcv = findViewById(R.id.contexttext);
                TextView  time = findViewById(R.id.timetext);
                TextView location = findViewById(R.id.locationtext);
                TextView floor = findViewById(R.id.floorholder);
                TextView room = findViewById(R.id.roomholder);
                workOject object = (workOject) model;
                namecv.setText(object.getNamecv().toString());
                contextcv.setText(object.getContextcv().toString());
                time.setText(object.getTimecv().toString());
                location.setText(object.getLocation().toString());
                floor.setText(object.getFloor().toString());
                room.setText(object.getRoom());
            }
        };
        lv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}