package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorRegisteration extends AppCompatActivity {

    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;

    FirebaseAuth userAuth;

    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registeration);

        final EditText donorEmail = findViewById(R.id.donorEmail);
        final EditText donorPhone = findViewById(R.id.donorPhone);
        final EditText donorAddress = findViewById(R.id.donorAddress);
        final EditText donorAge = findViewById(R.id.donorAge);
        final EditText donorGender = findViewById(R.id.donorGender);
        final EditText donorDisease = findViewById(R.id.donorDisease);
        final EditText donorBlood = findViewById(R.id.donorBlood);
        final EditText donorWeight = findViewById(R.id.donorWeight);
        final EditText donorAdhaar = findViewById(R.id.adhaar);
        final EditText donorName = findViewById(R.id.donorName);
        final EditText Password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.conPassword);
        final AppCompatButton signUp = findViewById(R.id.signUpET);
        final TextView signIn = findViewById(R.id.signInBtn);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conPasswordIcon = findViewById(R.id.conPasswordIcon);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking if password is shown or not
                if (passwordShowing) {
                    passwordShowing = false;
                    Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_show);
                } else {
                    passwordShowing = true;

                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_hide);


                    //moves the cursor till end of the password
                    Password.setSelection(Password.length());
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


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String donorEmailToString = convertToString(donorEmail);
                final String donorPhoneToString = convertToString(donorPhone);
                final String addressToString = convertToString(donorAddress);
                final String donorAdhaarToString = convertToString(donorAdhaar);
                final String donorAgeToString = convertToString(donorAge);
                final String donorBloodToString = convertToString(donorBlood);
                final String donorGenderToString = convertToString(donorGender);
                final String donorDiseaseToString = convertToString(donorDisease);
                final String donorWeightToString = convertToString(donorWeight);
                final String donorNameToString = convertToString(donorName);
                final String password = convertToString(Password);
                final String conPasswordToString = convertToString(conPassword);


                if (isEmpty(donorEmailToString) || isEmpty(addressToString) || isEmpty(donorAgeToString) || isEmpty(donorBloodToString) || isEmpty(donorGenderToString) || isEmpty(donorDiseaseToString) || isEmpty(donorWeightToString) || isEmpty(password) || isEmpty(conPasswordToString) || isEmpty(donorNameToString)) {
                    Toast.makeText(DonorRegisteration.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else if (stringLength(donorPhoneToString) != 10) {
                    donorPhone.setError("please enter a valid phone number");
                    donorPhone.requestFocus();
                } else if (stringLength(donorAdhaarToString) != 12) {
                    donorPhone.setError("please enter a valid adhaar number");
                    donorPhone.requestFocus();
                } else if (!password.equals(conPasswordToString)) {
                    conPassword.setError("password and confirm password should be equal");
                    conPassword.requestFocus();
                } else {
                    userAuth = FirebaseAuth.getInstance();

                    userAuth.createUserWithEmailAndPassword(donorEmailToString, conPasswordToString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                String uid = userAuth.getCurrentUser().getUid();
                                Log.d("qwerty", "Current USER UID ------------------- " + uid);


                                // write all the data entered by the user in SharedPreference and apply

                                userRef = FirebaseDatabase.getInstance().getReference("Donor Information");
                                userRef.child(uid).setValue(new UserHelperClass(donorNameToString, donorEmailToString, donorPhoneToString, addressToString, donorAgeToString, donorBloodToString, donorGenderToString, donorDiseaseToString, donorWeightToString, donorAdhaarToString, conPasswordToString));

                                Toast.makeText(DonorRegisteration.this, "Information saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DonorRegisteration.this, DonorDocuments.class);
                                SharedPreferences shrd = getSharedPreferences("hospital file", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = shrd.edit();
                                myEdit.putString("Username", donorNameToString);
                                myEdit.putString("User email", donorEmailToString);
                                myEdit.putString("User phoneNumber", donorPhoneToString);
                                myEdit.commit();
                                intent.putExtra("adhaar",donorAdhaarToString);
                                intent.putExtra("email",donorEmailToString);
                                intent.putExtra("phone",donorPhoneToString);
                                startActivity(intent);

                            } else {
                                Toast.makeText(DonorRegisteration.this, "Registration error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }


            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorRegisteration.this,DonorLogin.class));
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