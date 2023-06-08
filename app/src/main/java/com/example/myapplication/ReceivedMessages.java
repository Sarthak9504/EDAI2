package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.myapplication.databinding.ActivityRecivedMessagesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReceivedMessages extends AppCompatActivity {
        private DatabaseReference donorRequestRef;
        private ListView listView;
        private MyAdapter adapter;
        private ArrayList<PostData> postDataArrayList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recived_messages);

            listView = findViewById(R.id.receivedMessages);
            List<String> Name = new ArrayList<>();
            postDataArrayList = new ArrayList<>();
            //adapter = new MyAdapter(ReceivedMessages.this, postDataArrayList);
            //listView.setAdapter(adapter);

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            donorRequestRef = FirebaseDatabase.getInstance().getReference("Hospital information").child(uid).child("Donor request");
            donorRequestRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    postDataArrayList.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        PostData postData = postSnapshot.getValue(PostData.class);
                        String name = postData.getPatientName();
                        Name.add(name);
                        PostData hName = new PostData(name);
                        postDataArrayList.add(hName);

                        Log.d("QWERTY", "Data to add----------------> " + postData);
                        //postDataArrayList.add(postData);
                    }

                    adapter = new MyAdapter(ReceivedMessages.this, postDataArrayList);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error if needed
                }
            });
        }
    }


