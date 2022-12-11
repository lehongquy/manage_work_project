package com.example.managerworkofstatecadres.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hq.manager_work.R;
import com.squareup.picasso.Picasso;

public class screenSignup extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    String img;
    Button btncreate;
    EditText edtuser, edtpass, edtrepass, edtphone, edtgmail;
    TextView loginformsignup;
    LinearLayout layout;
    ImageView imageView;
    ProgressBar progressBar;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mosc-47a15-default-rtdb.firebaseio.com/");
    private Uri mImageUri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_signup);
        btncreate = findViewById(R.id.id_signupaccount);
        edtuser = findViewById(R.id.id_inputname);
        edtpass = findViewById(R.id.id_inputpass);
        edtrepass = findViewById(R.id.id_inputrepass);
        edtphone = findViewById(R.id.id_inputphone);
        edtgmail = findViewById(R.id.id_inputgmail);
        layout = findViewById(R.id.id_selectimg);
        imageView = findViewById(R.id.id_showimg);
        loginformsignup = findViewById(R.id.id_loginsignup);
        btncreate.setOnClickListener(v -> {
            createAccount();

        });
        loginformsignup.setOnClickListener(view -> {
            finish();
        });

        imageView.setOnClickListener(v -> {
            openFileChooser();
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(imageView);
             img= mImageUri.toString();


        }
    }

    void createAccount() {

        String userName = edtuser.getText().toString();
        String phone = edtphone.getText().toString();
        String gmail = edtgmail.getText().toString();
        String pass = edtpass.getText().toString();
        String position = "Low-level cadres";
        String vehicle = "vehicle" + phone;
        String work = "work" + phone;
        String notification = "notification" + phone;
        String repass = edtrepass.getText().toString();
        if (userName.isEmpty() || phone.isEmpty() || gmail.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(this, "Please input inforMain", Toast.LENGTH_SHORT).show();
        } else if (!pass.equals(repass)) {
            Toast.makeText(this, "Please input pass and repass together", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(phone)) {
                        Toast.makeText(screenSignup.this, "Phone is already", Toast.LENGTH_SHORT).show();
                    } else {

                        databaseReference.child("user").child(phone).child("fullname").setValue(userName);
                        databaseReference.child("user").child(phone).child("phone").setValue(phone);
                        databaseReference.child("user").child(phone).child("pass").setValue(pass);
                        databaseReference.child("user").child(phone).child("repass").setValue(repass);
                        databaseReference.child("user").child(phone).child("gmail").setValue(gmail);
                        databaseReference.child("user").child(phone).child("position").setValue(position);

                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("license").setValue("29D1-29854");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("trip").setValue("Bộ thông tin");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("namedriver").setValue("NguyenVanA");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("departure").setValue("9:00");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("people").setValue("2");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle1").child("critical").setValue("1");

                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("license").setValue("29D1-28345");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("trip").setValue("Bộ nông nghiệp");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("namedriver").setValue("NguyenVanB");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("departure").setValue("8:00");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("people").setValue("3");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle2").child("critical").setValue("1");

                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("license").setValue("29D1-28344");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("trip").setValue("Bộ quốc phòng");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("namedriver").setValue("NguyenVanc");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("departure").setValue("10:00");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("people").setValue("2");
                        databaseReference.child("user").child(phone).child(vehicle).child("vehicle3").child("critical").setValue("2");

                        databaseReference.child("user").child(phone).child(work).child("work1").child("namecv").setValue("Gặp bộ trưởng bô thông tin");
                        databaseReference.child("user").child(phone).child(work).child("work1").child("contextcv").setValue("Bàn về công nghệ mới trong lĩnh vực cntt");
                        databaseReference.child("user").child(phone).child(work).child("work1").child("timecv").setValue("10");
                        databaseReference.child("user").child(phone).child(work).child("work1").child("location").setValue("Cục cntt và tt");
                        databaseReference.child("user").child(phone).child(work).child("work1").child("floor").setValue("3");
                        databaseReference.child("user").child(phone).child(work).child("work1").child("room").setValue("P305");

                        databaseReference.child("user").child(phone).child(work).child("work2").child("namecv").setValue("Gặp bộ trưởng bô nông nghiệp");
                        databaseReference.child("user").child(phone).child(work).child("work2").child("contextcv").setValue("Bàn về phát triển đê ngăn lũ");
                        databaseReference.child("user").child(phone).child(work).child("work2").child("timecv").setValue("9");
                        databaseReference.child("user").child(phone).child(work).child("work2").child("location").setValue("Cục nông nghiệp");
                        databaseReference.child("user").child(phone).child(work).child("work2").child("floor").setValue("2");
                        databaseReference.child("user").child(phone).child(work).child("work2").child("room").setValue("H205");

                        databaseReference.child("user").child(phone).child(work).child("work3").child("namecv").setValue("Gặp bộ trưởng bô quốc phòng");
                        databaseReference.child("user").child(phone).child(work).child("work3").child("contextcv").setValue("Bàn về thay thế súng quân nhân");
                        databaseReference.child("user").child(phone).child(work).child("work3").child("timecv").setValue("2");
                        databaseReference.child("user").child(phone).child(work).child("work3").child("location").setValue("Bộ quốc phòng");
                        databaseReference.child("user").child(phone).child(work).child("work3").child("floor").setValue("1");
                        databaseReference.child("user").child(phone).child(work).child("work3").child("room").setValue("G105");

                        databaseReference.child("user").child(phone).child(notification).child("notification1").child("nament").setValue("họp khẩn bộ quốc phòng");
                        databaseReference.child("user").child(phone).child(notification).child("notification1").child("contextnt").setValue("thay đổi toàn bộ vũ khí chiến đấu của các chiến sĩ");
                        databaseReference.child("user").child(phone).child(notification).child("notification1").child("timent").setValue("10:00 am");
                        databaseReference.child("user").child(phone).child(notification).child("notification1").child("critical").setValue("3");

                        databaseReference.child("user").child(phone).child(notification).child("notification2").child("nament").setValue("họp khẩn các bộ");
                        databaseReference.child("user").child(phone).child(notification).child("notification2").child("contextnt").setValue("tăng trưởng kinh tế");
                        databaseReference.child("user").child(phone).child(notification).child("notification2").child("timent").setValue("2:00 pm");
                        databaseReference.child("user").child(phone).child(notification).child("notification2").child("critical").setValue("2");

                        databaseReference.child("user").child(phone).child(notification).child("notification3").child("nament").setValue("họp khẩn các sở");
                        databaseReference.child("user").child(phone).child(notification).child("notification3").child("contextnt").setValue("tăng trưởng kinh tế");
                        databaseReference.child("user").child(phone).child(notification).child("notification3").child("timent").setValue("3:00 pm");
                        databaseReference.child("user").child(phone).child(notification).child("notification3").child("critical").setValue("1");
                        startActivity(new Intent(screenSignup.this, screenLogin.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}