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
        switch (position) {
            case 1:
                return guestInfor.newInstance();

            default: return qrCode.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
