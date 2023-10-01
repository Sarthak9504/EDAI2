package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class DonorDocuments extends AppCompatActivity {

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_documents);

        final Button upload = findViewById(R.id.upload1);
        final Button done = findViewById(R.id.done1);
        final Spinner mySpinner  = findViewById(R.id.spinner);
        mySpinner.setContentDescription("Select an option");
        String[] items = new String[]{"Form 7", "Form 10","other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        mySpinner.setAdapter(adapter);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent,1);

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorDocuments.this,QRCode.class);
                final String adhaar = getIntent().getStringExtra("adhaar");
                final String email = getIntent().getStringExtra("email");
                final String phone = getIntent().getStringExtra("phone");
                intent.putExtra("adhaar",adhaar);
                intent.putExtra("email",email);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data !=null && data.getData()!=null){
            uri = data.getData();
            Toast.makeText(DonorDocuments.this, "Documents Uploaded successfully", Toast.LENGTH_SHORT).show();

            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference pdfRef = storageReference.child("pdfs/" + UUID.randomUUID().toString() + ".pdf");

            pdfRef.putFile(uri).addOnSuccessListener(taskSnapshot -> {
                pdfRef.getDownloadUrl().addOnSuccessListener(uri1 -> {
                    String downloadURL = uri.toString();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Donor Profile");
                    FirebaseAuth userAuth = FirebaseAuth.getInstance();
                    String uid = userAuth.getCurrentUser().getUid();
                    databaseReference.child(uid).setValue(downloadURL);
                });
            });
        }
      }
    }
