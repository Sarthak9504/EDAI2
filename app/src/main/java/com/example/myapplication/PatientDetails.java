package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrient_details);

        final EditText patientName = findViewById(R.id.patientName);
        final EditText patientAge = findViewById(R.id.patientAge);
        final EditText patientGender = findViewById(R.id.patientGender);
        final EditText bloodGroup = findViewById(R.id.bloodGroup);
        final EditText bloodPressure = findViewById(R.id.bP);
        final EditText disease = findViewById(R.id.disease);
        final EditText weight = findViewById(R.id.weight);
        final AppCompatButton save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String patientNameTxt = patientName.getText().toString();
                final String patientAgeTxt = patientAge.getText().toString();
                final String patientGenderTxt = patientGender.getText().toString();
                final String bloodGroupTxt = bloodGroup.getText().toString();
                final String diseaseTxt = disease.getText().toString();
                final String WeightTxt = weight.getText().toString();
                final String bloodPressureTxt = bloodPressure.getText().toString();


                if (isEmpty(patientNameTxt) || isEmpty(patientAgeTxt) || isEmpty(patientGenderTxt)
                        || isEmpty(bloodGroupTxt) || isEmpty(diseaseTxt) || isEmpty(WeightTxt)
                        || isEmpty(bloodPressureTxt) ) {
                    Toast.makeText(PatientDetails.this, "Please fill all the details !!", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Hospital Information");
                    PatientClass patientClass = new PatientClass(patientNameTxt, patientAgeTxt, patientGenderTxt, bloodGroupTxt,
                            diseaseTxt, WeightTxt,bloodPressureTxt);
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    userRef.child(uid).child("Patient Details").child(patientNameTxt).setValue(patientClass);
                    Toast.makeText(PatientDetails.this, "Message sent successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(PatientDetails.this, Dashboard.class));
                }



            }
        });

    }
    private boolean isEmpty(String string) {
        return string.isEmpty();
    }
}