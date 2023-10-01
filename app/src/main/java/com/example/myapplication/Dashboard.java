package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final CardView fillProfile = findViewById(R.id.fillProfile);
        final CardView patientDetails = findViewById(R.id.patientDetails);
        final CardView donorDetails = findViewById(R.id.donorDetails);
        final CardView addPost = findViewById(R.id.addPost);
        final CardView message = findViewById(R.id.message);

        fillProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dashboard.this,FillProfile.class);
                final String Uid = intent.getStringExtra("Uid");
                intent.putExtra("Uid",Uid);
                startActivity(intent);
            }
        });

        patientDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this,PatientDetails.class);
                final String Uid = intent.getStringExtra("Uid");
                intent.putExtra("Uid",Uid);
                startActivity(intent);
            }
        });

        donorDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this,DonorDetails.class);
                final String Uid = intent.getStringExtra("Uid");
                intent.putExtra("Uid",Uid);
                startActivity(intent);
            }
        });

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this,AddPost.class);
                startActivity(intent);


            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, ReceivedMessages.class));

            }
        });


    }
}