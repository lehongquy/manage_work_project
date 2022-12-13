package com.example.managerworkofstatecadres.qr;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.hq.manager_work.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class guestInfor extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");
    EditText edtphone, edtpass;
    ImageView showqrcode;
    TextView btnfind, inforguest;
    String one = "", text = "Tính năng đang trong giai đoạn phát triển";

    public static Fragment newInstance() {
        return new guestInfor();
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guest, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 600, 600);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            showqrcode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void canbo(String text1) {
        text = text1;
    }

    public void initView(View view) {
        edtphone = view.findViewById(R.id.id_inputphone);
        edtpass = view.findViewById(R.id.id_inputpass);
        btnfind = view.findViewById(R.id.id_find);
        inforguest = view.findViewById(R.id.infor_guest);
        btnfind.setOnClickListener(v -> one1());
        showqrcode = view.findViewById(R.id.showqr);

    }

    public void one1() {

        String phone = edtphone.getText().toString();
        String pass = edtpass.getText().toString();
        if (phone.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getActivity(), "Please,Input pass or phone", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phone)) {
                        final String getPass = snapshot.child(phone).child("pass").getValue(String.class);
                        if (getPass.equals(pass)) {
                            String txt = read();
                            inforguest.setText(txt);
                            Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(getActivity(), "Input pass fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Don't find account", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private String read() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user").child(edtphone.getText().toString());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inforGuest infor = snapshot.getValue(inforGuest.class);
                one = infor.toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return one;
    }
}