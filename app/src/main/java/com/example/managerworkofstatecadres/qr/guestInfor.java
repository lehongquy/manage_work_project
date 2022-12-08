package com.example.managerworkofstatecadres.qr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;


public class guestInfor extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");
    EditText edtphone ,edtpass;

    TextView btnfind;


    public guestInfor() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edtphone = getActivity().findViewById(R.id.id_inputphone);
        edtpass = getActivity().findViewById(R.id.id_inputpass);
        btnfind=getActivity().findViewById(R.id.id_find);
        btnfind.setOnClickListener(v-> one());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest, container, false);
    }
    public void  one(){

         String phone =edtphone.getText().toString();
         String pass =edtpass.getText().toString();
        if (phone.isEmpty()||pass.isEmpty()){
            Toast.makeText(getActivity(), "Please,Input pass or phone", Toast.LENGTH_SHORT).show();
        }else{
            databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phone)){
                        final  String getPass = snapshot.child(phone).child("pass").getValue(String.class);
                        if (getPass.equals(pass)){
                            guestInforControl infor =snapshot.getValue(guestInforControl.class);
                            String txt = infor.getFullname()+"\n"+infor.getGmail()+"\n"+infor.getPhone()+"\n"+infor.getPosition();
                            Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("dataguest", txt);

                                dialogInfor fragobj = new dialogInfor();
                                fragobj.setArguments(bundle);

                        }else {
                            Toast.makeText(getActivity(), "Input pass fail", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(), "Don't find account", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}