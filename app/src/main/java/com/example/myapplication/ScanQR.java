package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQR extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Hospital Information");

        // Start QR code scanner
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            // Scanned QR code data
            String scannedData = result.getContents();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("DonorsList");
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            saveScannedData(uid, scannedData);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void saveScannedData(String uid, String scannedData) {

        ScannedQR scannedQR = new ScannedQR(scannedData);
        databaseReference.child(uid).push().setValue(scannedQR)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ScanQR.this, "Data Saved", Toast.LENGTH_SHORT).show();
                        Log.d("ScanQRActivity", "Scanned data saved to Firebase");
                    } else {
                        Toast.makeText(ScanQR.this, "Failed to save scanned data", Toast.LENGTH_SHORT).show();
                        Log.e("ScanQRActivity", "Failed to save scanned data: " + task.getException());
                    }
                    finish();
                });
    }
}
