package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        final EditText emgNo = findViewById(R.id.emgPhone);
        final EditText emgEmail = findViewById(R.id.emgEmail);
        final EditText organEmail = findViewById(R.id.organEmail);
        final EditText organNo = findViewById(R.id.organPhone);
        final EditText dinNo = findViewById(R.id.dinNo);
        final EditText formReg = findViewById(R.id.formReg);


        final String directEmailToString = convertToString(directEmail);
        final String directPhoneToString = convertToString(directPhone);
        final String addLine1ToString = convertToString(addLine1);
        final String addLine2ToString = convertToString(addLine2);
        final String addLine3ToString = convertToString(addLine3);
        final String yearToString = convertToString(year);
        final String adminNoToString = convertToString(adminNo);
        final String adminEmailToString = convertToString(adminEmail);
        final String emgNoToString = convertToString(emgNo);
        final String emgEmailToString = convertToString(emgEmail);
        final String organEmailToString = convertToString(organEmail);
        final String organNoToString = convertToString(organNo);
        final String dinNoToString = convertToString(dinNo);
        final String formRegToString = convertToString(formReg);
      ;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(directEmailToString) || isEmpty(directPhoneToString) || isEmpty(addLine1ToString) || isEmpty(addLine2ToString) ||
                        isEmpty(addLine3ToString) || isEmpty(yearToString) || isEmpty(adminEmailToString) || isEmpty(adminNoToString) || isEmpty(emgNoToString) ||
                        isEmpty(emgEmailToString) || isEmpty(organEmailToString) || isEmpty(organNoToString) || isEmpty(dinNoToString) || isEmpty(formRegToString)
                        )
                {
                    Toast.makeText(FillProfile.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else if (stringLength(directPhoneToString) != 10 || stringLength(yearToString) != 10 || stringLength(adminNoToString) != 10 ||
                        stringLength(emgNoToString) != 10 || stringLength(organNoToString) != 10 || stringLength(dinNoToString) != 8 ||
                        stringLength(formRegToString) != 9)
                {
                    Toast.makeText(FillProfile.this, "Please enter Valid details", Toast.LENGTH_SHORT).show();
                } else {
                    userRef = FirebaseDatabase.getInstance().getReference("Hospital Information");
                  //  HospitalInformation hospitalInformation = new HospitalInformation(directPhoneToString,directEmailToString);
                    userRef.child("Director's Details").setValue(new HospitalInformation(directPhoneToString,directEmailToString));
                    HospitalInformation hospitalAddress = new HospitalInformation(addLine1ToString,addLine2ToString,addLine3ToString);
                    userRef.child("name").setValue(hospitalAddress);
                    userRef.child("name").setValue(new HospitalInformation(yearToString));
                    userRef.child("Admin Info").setValue(adminNoToString,add);

                    startActivity(new Intent(FillProfile.this, UploadDocuments.class));}
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