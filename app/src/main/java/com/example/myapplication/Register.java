package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;
    FirebaseAuth userAuth;
    FirebaseDatabase rootNode;
    DatabaseReference userRef;


    UserHelperClass userHelper = new UserHelperClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);

        final EditText email = findViewById(R.id.emailET);
        final EditText mobile = findViewById(R.id.mobileET);
        final EditText conPassword = findViewById(R.id.conPasswordET);
        final EditText password = findViewById(R.id.passwordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conPasswordIcon = findViewById(R.id.conPasswordIcon);
        final Button signUpBtn = findViewById(R.id.regbtn);
        final TextView signInBtn = findViewById(R.id.signInBtn);
        final EditText fullName = findViewById(R.id.fullNameET);


        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking if password is shown or not
                if (passwordShowing) {
                    passwordShowing = false;
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_show);
                } else {
                    passwordShowing = true;

                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_hide);


                    //moves the cursor till end of the password
                    password.setSelection(password.length());
                }


            }
        });


        conPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking if password is shown or not
                if (conPasswordShowing) {
                    conPasswordShowing = false;
                    conPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.password_show);
                } else {
                    conPasswordShowing = true;

                    conPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.password_hide);


                }

                //moves the cursor till end of the password
                conPassword.setSelection(conPassword.length());

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shifting to otp verification portal
                final String getMobileTxt = mobile.getText().toString();
                final String getEmailTxt = email.getText().toString();
                final String passwordToString = password.getText().toString();
                final String conPasswordToString = conPassword.getText().toString();
                final String name = fullName.getText().toString();


                if (name.isEmpty()) {
                    fullName.setError("Name cannot be empty");
                    fullName.requestFocus();
                } else if (TextUtils.isEmpty(getEmailTxt)) {//checking email is filled or not
                    email.setError("Email cannot be empty");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(getMobileTxt)) {//checking mobile number is filled or not
                    mobile.setError("Mobile number cannot be empty");
                    mobile.requestFocus();
                } else if (getMobileTxt.length() != 10) {
                    mobile.setError("Invalid mobile number");
                    mobile.requestFocus();
                } else if (!passwordToString.equals(conPasswordToString)) {
                    conPassword.setError("Confirm Password should be same as the password");
                    conPassword.requestFocus();
                } else {

                    userAuth = FirebaseAuth.getInstance();
                    rootNode = FirebaseDatabase.getInstance();
                    userRef = rootNode.getReference("Hospital Information");

                    UserHelperClass userHelperClass = new UserHelperClass(getEmailTxt, getMobileTxt, conPasswordToString);

                    userRef.child(name).setValue(userHelperClass);

                    userAuth = FirebaseAuth.getInstance();

                    userRef.child(name).setValue(userHelperClass);
                    userAuth.createUserWithEmailAndPassword(getEmailTxt, conPasswordToString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Dashboard.class);
                                intent.putExtra("email", getEmailTxt);
                                intent.putExtra("password", passwordToString);
                                intent.putExtra("fullName", name);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Registration Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }

}