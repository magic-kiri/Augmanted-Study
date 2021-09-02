package com.example.augmantedreality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    EditText emailId,password;
    Button logInbtn;
    TextView signUp;
    FirebaseAuth mFirebaseAuth;
    private  FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId  = findViewById(R.id.logInEmail);
        password = findViewById(R.id.logInPassword);
        mFirebaseAuth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.logInSignUp);
        logInbtn = findViewById(R.id.logInLogIn);


        if(mFirebaseAuth.getCurrentUser()!= null)
        {
            mFirebaseAuth.signOut();
        }

        logInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
                if(everythigIsValid(email,pass)) // This function has been created manually to check whether the email and passwords are valid or not.
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LogInActivity.this, "Logged In :)", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LogInActivity.this,BasementActivity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LogInActivity.this, "Enter a valid Email and Password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this,SignUpActivity.class));
                finish();
            }
        });
    }

    private boolean everythigIsValid(String email, String pass)
    {
        if(email.isEmpty() && pass.isEmpty())
        {
            Toast.makeText(LogInActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(email.isEmpty())
        {
            emailId.setError("Please enter Email ID");
            emailId.requestFocus();
            return false;
        }
        else if(pass.isEmpty())
        {
            password.setError("Please enter your password!");
            password.requestFocus();
            return false;
        }
        else
            return true;
    }

}
