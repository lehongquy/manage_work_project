package com.example.managerworkofstatecadres.qr;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class myViewPage extends FragmentStateAdapter {
    public myViewPage(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new qrCode() ;
            case 1:
                return new guestInfor();
            default:
                return new qrCode();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
