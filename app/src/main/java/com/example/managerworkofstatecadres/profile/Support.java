package com.example.managerworkofstatecadres.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.hq.manager_work.R;

public class Support extends Dialog {

    interface FullNameListener {
        public void fullNameEntered(String fullName);
    }

    public Context context;


    private Button buttonOK;

    private Support.FullNameListener listener;

    public Support(Context context, Support.FullNameListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_support);

        this.buttonOK = findViewById(R.id.ok);

        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });

    }

    // User click "OK" button.
    private void buttonOKClick()  {

        this.dismiss(); // Close Dialog


    }

}