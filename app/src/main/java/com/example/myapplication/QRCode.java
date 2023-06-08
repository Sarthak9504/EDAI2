package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class QRCode extends AppCompatActivity {
    private static final int REQUEST_WRITE_STORAGE = 112;
    ImageView qrCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

         qrCode = findViewById(R.id.qrCode);
        final AppCompatButton generateQR = findViewById(R.id.generateQR);
        final AppCompatButton save = findViewById(R.id.save);
        // Check if permission is not granted

        generateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String adhaar = getIntent().getStringExtra("donor adhaar");
                String donorName = getIntent().getStringExtra("donor name");


                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    BitMatrix bitMatrix = multiFormatWriter.encode(uid, BarcodeFormat.QR_CODE, 350, 350);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCode.setImageBitmap(bitmap);
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }


            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the QR code bitmap from the ImageView
                Bitmap qrCodeBitmap = ((BitmapDrawable) qrCode.getDrawable()).getBitmap();

                // Convert the bitmap to a base64 string
                String base64Image = convertBitmapToBase64(qrCodeBitmap);


                // Save the base64 string to Firebase Realtime Database
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Donor Information");
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference qrCodeRef = databaseRef.child(uid).push(); // Create a new unique key for the QR code
                qrCodeRef.setValue(base64Image)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // QR code image saved successfully
                                Toast.makeText(QRCode.this, "QR code saved successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error saving QR code image
                                Toast.makeText(QRCode.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        });
                startActivity(new Intent(QRCode.this,DonorDashboard.class));
            }
        });





    }
    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    /*private void saveQRCodeToGallery() {
        if (ContextCompat.checkSelfPermission(QRCode.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(QRCode.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        } else {
            // Get the QR code bitmap from the ImageView
            Bitmap qrCodeBitmap = ((BitmapDrawable) qrCode.getDrawable()).getBitmap();

            // Save the QR code bitmap to the gallery
            String savedImageURL = MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    qrCodeBitmap,
                    "QR Code",
                    "QR Code generated by My Application"
            );

            if (savedImageURL != null) {
                Toast.makeText(QRCode.this, "QR code saved to gallery", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QRCode.this, "Failed to save QR code", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveQRCodeToGallery();
            } else {
                Toast.makeText(QRCode.this, "Permission denied. Cannot save QR code to gallery.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

}



