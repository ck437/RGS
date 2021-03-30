package com.example.rgs;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class test extends AppCompatActivity {
    private TextView textViewInfo;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth;
    private CollectionReference Ref = db.collection("Complaints");
    String UserID;
    String complaintlist;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Spinner mComplaints = findViewById(R.id.spinner);
        ArrayAdapter<String> myAdaptor = new ArrayAdapter<>(test.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.complaint_types));
        myAdaptor.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mComplaints.setAdapter(myAdaptor);

        mComplaints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                complaintlist = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textViewInfo = findViewById(R.id.text_view_info);
        show = findViewById(R.id.button);
        fAuth = FirebaseAuth.getInstance();
        UserID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Ref.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!= null) {
                    return;
                }
                StringBuilder info = new StringBuilder();
                assert queryDocumentSnapshots != null;
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Complaints comp = documentSnapshot.toObject(Complaints.class);
                    comp.setDocumentId(documentSnapshot.getId());
                    String documentId = comp.getDocumentId();
                    String userid = comp.getUserid();
                    String complaint = comp.getComplaint();
                    String landmark = comp.getLandmark();
                    String address = comp.getAddress();
                    String problem = comp.getProblem();
                    if(userid.equals(UserID) && complaint.equals(complaintlist)) {
                        info.append("ID\t: ").append(documentId).append("\nuserid\t: ").append(userid).append("\ncomplaint\t: ").append(complaint).append("\nlandmark\t: ").append(landmark).append("\naddress\t: ").append(address).append("\nproblem\t: ").append(problem).append("\n\n");
                    }
                }
                textViewInfo.setText(info.toString());
            }
        });

    }



}

