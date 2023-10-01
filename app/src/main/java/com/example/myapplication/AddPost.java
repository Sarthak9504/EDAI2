package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddPost extends AppCompatActivity {


    DatabaseReference userRef;
    FirebaseDatabase rootNode;

    long messageID = System.currentTimeMillis();
    long messageId = System.currentTimeMillis();


    //FirebaseAuth  FirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        final EditText patientName = findViewById(R.id.patientName);
        final EditText hospitalName = findViewById(R.id.hospitalName);
        final EditText patientAge = findViewById(R.id.patientAge);
        final EditText patientGender = findViewById(R.id.patientGender);
        final EditText bloodGroup = findViewById(R.id.bloodGroup);
        final EditText disease = findViewById(R.id.disease);
        final EditText organSize = findViewById(R.id.organSize);
        final EditText organType = findViewById(R.id.organType);
        final AppCompatButton sendBtn = findViewById(R.id.send);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String patientNameTxt = patientName.getText().toString();
                final String patientAgeTxt = patientAge.getText().toString();
                final String patientGenderTxt = patientGender.getText().toString();
                final String bloodGroupTxt = bloodGroup.getText().toString();
                final String diseaseTxt = disease.getText().toString();
                final String organTypeTxt = organType.getText().toString();
                final String organSizeTxt = organSize.getText().toString();
                final String hospitalNameToString = hospitalName.getText().toString();


                //String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                //Log.d("qwerty", "Current USER UID ------------------- "+ uid);

                if (isEmpty(patientNameTxt) || isEmpty(patientAgeTxt) || isEmpty(patientGenderTxt)
                        || isEmpty(bloodGroupTxt) || isEmpty(diseaseTxt) || isEmpty(organTypeTxt)
                        || isEmpty(organSizeTxt) || isEmpty(hospitalNameToString)) {
                    Toast.makeText(AddPost.this, "Please fill all the details !!", Toast.LENGTH_SHORT).show();
                } else {
                    rootNode = FirebaseDatabase.getInstance();
                    userRef = rootNode.getReference("Hospital Information");
                    PostData postData = new PostData(hospitalNameToString,patientNameTxt, patientAgeTxt, patientGenderTxt, bloodGroupTxt,
                            diseaseTxt, organTypeTxt, organSizeTxt);
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    userRef.child(uid).child("Donor Request").child(patientNameTxt).setValue(postData);
                    Toast.makeText(AddPost.this, "Message sent successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AddPost.this, Dashboard.class));
                }
            }

        });
    }

    private boolean isEmpty(String string) {
        return string.isEmpty();
    }
}


