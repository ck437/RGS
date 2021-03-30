package com.example.rgs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Uhome extends AppCompatActivity {
    private FirebaseAuth fauth;
    Button registercomplaint,myprofile,viewcomplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uhome);

        fauth = FirebaseAuth.getInstance();
        registercomplaint = findViewById(R.id.registercomplaint);
        viewcomplaints = findViewById(R.id.viewcomplaints);
        myprofile = findViewById(R.id.myprofile);
        Button logout = findViewById(R.id.logout);

        registercomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), register_complaint.class));
            }
        });

        viewcomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Uhome.this,test.class));
            }
        });

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UProfile.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fauth.signOut();
                finish();
                startActivity(new Intent(Uhome.this,UserLogin.class));
            }
        });
    }
}
