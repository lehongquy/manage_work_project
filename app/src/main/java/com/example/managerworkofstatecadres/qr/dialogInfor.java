package com.example.managerworkofstatecadres.qr;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hq.manager_work.R;


public class dialogInfor extends DialogFragment {
    String TAG="Infor";
    private TextView txt,actionCancle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_dialog,container,false );
        txt =getActivity().findViewById(R.id.id_name);
        actionCancle =getActivity().findViewById(R.id.id_cancle);
        String text= getArguments().getString("dataguest");
        txt.setText(text);
        actionCancle.setOnClickListener(v -> {
            getDialog().dismiss();
        });


        return view;
    }
}