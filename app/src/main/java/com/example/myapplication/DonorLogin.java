package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DonorLogin extends AppCompatActivity {

    FirebaseAuth userAuth;

    private boolean passwordShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_login);

        final EditText email = findViewById(R.id.userEmail);

        final EditText passwordET = findViewById(R.id.passwordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);

        final TextView signUpBtn = findViewById(R.id.signUpBtn);
        final AppCompatButton signInBtn = findViewById(R.id.signInBtn);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking if password is shown or not
                if (passwordShowing) {
                    passwordShowing = false;
                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_show);
                } else {
                    passwordShowing = true;

                    passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_hide);

                }
                //moves the cursor till end of the password
                passwordET.setSelection(passwordET.length());
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userEmail = email.getText().toString();
                final String userPassword = passwordET.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {//checking email is filled or not
                    email.setError("Email cannot be empty");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(userPassword)) {//checking email is filled or not
                    email.setError("Password cannot be empty");
                    email.requestFocus();
                } else {

                    userAuth = FirebaseAuth.getInstance();
                    userAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(DonorLogin.this, DonorDashboard.class));
                            } else {
                                Toast.makeText(DonorLogin.this, "User not registered ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorLogin.this, DonorRegisteration.class));
            }
        });


    }
    }
