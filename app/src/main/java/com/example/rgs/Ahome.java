package com.example.rgs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ahome extends AppCompatActivity {
    private Button logout;
    Button viewcomplaints,viewuprofiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahome);

        viewcomplaints = findViewById(R.id.viewcomplaints);
        viewuprofiles = findViewById(R.id.viewuprofiles);
        logout = findViewById(R.id.logout);

        viewcomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),aviewcomplaints.class));
            }
        });

        viewuprofiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Aveiwusers.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ahome.this,AdminLogin.class));
                finish();
            }
        });
    }
}
