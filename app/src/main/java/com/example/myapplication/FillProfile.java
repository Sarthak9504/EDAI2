package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FillProfile extends AppCompatActivity {

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
        final EditText validFrom = findViewById(R.id.from);
        final EditText validUpto = findViewById(R.id.to);

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
        final String validFromToString = convertToString(validFrom);
        final String validUptoToString = convertToString(validUpto);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(directEmailToString) || isEmpty(directPhoneToString) || isEmpty(addLine1ToString) || isEmpty(addLine2ToString) ||
                        isEmpty(addLine3ToString) || isEmpty(yearToString) || isEmpty(adminEmailToString) || isEmpty(adminNoToString) || isEmpty(emgNoToString) ||
                        isEmpty(emgEmailToString) || isEmpty(organEmailToString) || isEmpty(organNoToString) || isEmpty(dinNoToString) || isEmpty(formRegToString) ||
                        isEmpty(validFromToString) || isEmpty(validUptoToString))
                {
                    Toast.makeText(FillProfile.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else if (stringLength(directPhoneToString) != 10 || stringLength(yearToString) != 10 || stringLength(adminNoToString) != 10 ||
                        stringLength(emgNoToString) != 10 || stringLength(organNoToString) != 10 || stringLength(dinNoToString) != 8 ||
                        stringLength(formRegToString) != 9)
                {
                    Toast.makeText(FillProfile.this, "Please enter Valid details", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(FillProfile.this, UploadDocuments.class));
                }
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