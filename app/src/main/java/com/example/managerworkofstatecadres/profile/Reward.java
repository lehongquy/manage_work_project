package com.example.managerworkofstatecadres.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hq.manager_work.R;

import java.util.ArrayList;


public class Reward extends AppCompatActivity {
    ListView listreward, listdiscip;
    ArrayList<String> listProduct;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        listProduct = new ArrayList<>();
        listProduct.add("Tặng huân chương lao động hạng 3");
        listProduct.add("Thăng chức thứ trưởng");
        listProduct.add("Thăng chức phó thủ tướng");
        listreward = findViewById(R.id.listreward);
        listdiscip = findViewById(R.id.listdiscip);
        listreward.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listProduct));
    }
}