package com.example.augmantedreality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.augmantedreality.ui.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    EditText emailId,password,name,phoneNo,birthDate;
    Button signUp;
    TextView logIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activty);

        mFirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.signUpName);
        emailId = findViewById(R.id.signUpEmail);
        password = findViewById(R.id.signUpPassword);
        phoneNo = findViewById(R.id.signUpPhone);
        birthDate = findViewById(R.id.signUpDate);
        signUp = findViewById(R.id.signUpSignUp);
        logIn = findViewById(R.id.signUpLogIn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                final String username =  name.getText().toString().trim();
                final String phone = phoneNo.getText().toString().trim();
                final String birthDay = birthDate.getText().toString().trim();

                if(everythigIsValid(email,pass,username,phone,birthDay)) // This function has been created manually to check whether the email and passwords are valid or not.
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful() )
                            {
                                DatabaseReference root = FirebaseDatabase.getInstance().getReference("Store");
                                String id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();  // Created an ID;
                                User newAccount = new User(username,email, pass,phone,birthDay,id);
                                root.child(id).setValue(newAccount);
                                Toast.makeText(SignUpActivity.this, "Please Log In!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignUpActivity.this, "Enter a valid Email and Password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                finish();
            }
        });
    }

    private boolean everythigIsValid(String email, String pass,String username,String phone,String birthDay)
    {

        if(email.isEmpty() && pass.isEmpty() && username.isEmpty() && phone.isEmpty() && birthDay.isEmpty())
        {
            Toast.makeText(SignUpActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(email.isEmpty())
        {
            emailId.setError("Please enter Email ID!");
            emailId.requestFocus();
            return false;
        }
        else if(pass.isEmpty())
        {
            password.setError("Please enter your password!");
            password.requestFocus();
            return false;
        }
        else if(username.isEmpty())
        {
            name.setError("Please enter your name!");
            name.requestFocus();
            return false;
        }
        else if(phone.isEmpty())
        {
            phoneNo.setError("Please enter your Phone Number!");
            phoneNo.requestFocus();
            return false;
        }
        else if(birthDay.isEmpty())
        {
            birthDate.setError("Please enter your BirthDay");
            birthDate.requestFocus();
            return false;
        }
        else
            return true;
    }


}
