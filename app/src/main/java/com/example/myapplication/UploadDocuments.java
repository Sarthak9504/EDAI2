package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayDeque;
import java.util.UUID;

import kotlin.coroutines.Continuation;

public class UploadDocuments extends AppCompatActivity {
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_documents);

        final Button upload = findViewById(R.id.upload);
        final Button done = findViewById(R.id.done);
        Spinner mySpinner  = findViewById(R.id.spinner1);
        mySpinner.setContentDescription("Select an option");
        String[] items = new String[]{"Form C", "Government issued license","other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        mySpinner.setAdapter(adapter);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent.createChooser(galleryIntent,"Select PDF files...."),1);


            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadDocuments.this,Dashboard.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data !=null && data.getData()!=null){
            uri = data.getData();
            Toast.makeText(UploadDocuments.this, "Documents Uploaded successfully", Toast.LENGTH_SHORT).show();


            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference pdfRef = storageReference.child("uploads/" + UUID.randomUUID().toString() + ".pdf");

            pdfRef.putFile(uri).addOnSuccessListener(taskSnapshot -> {
                pdfRef.getDownloadUrl().addOnSuccessListener(uri1 -> {
                    String downloadURL = uri1.toString();
                    DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReference("Hospital Information");
                    FirebaseAuth userAuth = FirebaseAuth.getInstance();
                    String uid = userAuth.getCurrentUser().getUid();
                    databaseReference.child(uid).setValue(downloadURL);
                });
            });
        }
    }
}