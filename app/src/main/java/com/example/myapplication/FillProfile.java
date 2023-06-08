package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FillProfile extends AppCompatActivity {


    DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_profile);

        final AppCompatButton next = findViewById(R.id.next);
        final EditText directEmail = findViewById(R.id.directEmail);
        final EditText directPhone = findViewById(R.id.directPhone);
        final EditText addLine1 = findViewById(R.id.addLine1);
        final EditText addLine2 = findViewById(R.id.addLine2);
        final EditText addLine3 = findViewById(R.id.addLine3);
        final EditText year = findViewById(R.id.year);
        final EditText adminNo = findViewById(R.id.adminNo);
        final EditText adminEmail = findViewById(R.id.adminEmail);
        final EditText dinNo = findViewById(R.id.dinNo);
        final EditText formCRegistrationNumber = findViewById(R.id.formReg);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String directEmailToString = convertToString(directEmail);
                final String directPhoneToString = convertToString(directPhone);
                final String addLine1ToString = convertToString(addLine1);
                final String addLine2ToString = convertToString(addLine2);
                final String addLine3ToString = convertToString(addLine3);
                final String yearToString = convertToString(year);
                final String adminNoToString = convertToString(adminNo);
                final String adminEmailToString = convertToString(adminEmail);
                final String dinNoToString = convertToString(dinNo);
                final String formC_RegistrationNumber = convertToString(formCRegistrationNumber);

                    userRef = FirebaseDatabase.getInstance().getReference("Hospital Profile");

                    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Log.d("qwerty", "Current USER UID ------------------- " + uid);

                HospitalInformation hospitalInformation1 = new HospitalInformation(directPhoneToString, directEmailToString);
                HospitalInformation hospitalInformation2 = new HospitalInformation(adminNoToString, adminEmailToString);
                HospitalInformation hospitalInformation3 = new HospitalInformation(addLine1ToString, addLine2ToString, addLine3ToString);
                HospitalInformation hospitalInformation4 = new HospitalInformation(yearToString);
                HospitalLiscense hospitalLiscense = new HospitalLiscense(dinNoToString, formC_RegistrationNumber);

                userRef.child(uid).push().setValue(hospitalInformation4);
                userRef.child(uid).push().setValue(hospitalLiscense);
                userRef.child(uid).child("Director's Info").push().setValue(hospitalInformation1);
                userRef.child(uid).child("Admin details").push().setValue(hospitalInformation2);
                userRef.child(uid).child("Address").push().setValue(hospitalInformation3);

                    userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Log.d("QWERTY", "------------>"+ );

                        Toast.makeText(FillProfile.this, "Profile Saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FillProfile.this, UploadDocuments.class));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(FillProfile.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private String convertToString(EditText string) {
        return string.getText().toString();
    }

    private boolean isEmpty(String string) {
        return string.isEmpty();
    }

    private int stringLength(String string) {
        return string.length();
    }
}