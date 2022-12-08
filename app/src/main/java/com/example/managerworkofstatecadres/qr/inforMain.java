package com.example.managerworkofstatecadres.qr;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hq.manager_work.R;


public class inforMain extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        viewPager2 = findViewById(R.id.viewpage2);
        view = findViewById(R.id.bottomNv);
        myViewPage page = new myViewPage(this);
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
}