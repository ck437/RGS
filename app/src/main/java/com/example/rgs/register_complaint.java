package com.example.rgs;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class register_complaint  extends AppCompatActivity implements View.OnClickListener {

    private EditText elandmark,eproblem,eaddress;
    String UserID,ecomplaintlist;
    FirebaseAuth fAuth;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);

        final Spinner mComplaints = findViewById(R.id.complaintlist);
        ArrayAdapter<String> myAdaptor = new ArrayAdapter<>(register_complaint.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.complaint_types));
        myAdaptor.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mComplaints.setAdapter(myAdaptor);

        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        elandmark = findViewById(R.id.elandmark);
        eaddress = findViewById(R.id.eaddress);
        eproblem = findViewById(R.id.problem);
        UserID = fAuth.getCurrentUser().getUid();

        mComplaints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ecomplaintlist = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.registercomplaintbtn).setOnClickListener(this);
    }

    private void saveComplaint(){
        String landmark = elandmark.getText().toString().trim();
        String address = eaddress.getText().toString().trim();
        String problem = eproblem.getText().toString().trim();
        String complaintlist = ecomplaintlist;


        if (true) {

            CollectionReference dbProducts = db.collection("Complaints");

            Complaints comp = new Complaints(UserID,landmark,address,complaintlist,problem);



            dbProducts.add(comp)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(register_complaint.this, "Complaint Registered", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), register_complaint.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(register_complaint.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {
        saveComplaint();
    }
}

