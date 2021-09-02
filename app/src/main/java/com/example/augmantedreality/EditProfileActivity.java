package com.example.augmantedreality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.augmantedreality.ui.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EditProfileActivity extends AppCompatActivity {
    private TextView cancel;
    private Button update;


    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    public static ArrayList<BarEntry> barEntries = new ArrayList<>();
    public static User tmpUser;
    public static BarChart barChart;
    public static BarDataSet barDataSet;
    public static ArrayList<String> theDates;
    public static BarData theData;
    ImageView avatar;
    TextView nav_username;
    TextView nav_email;
    FirebaseUser user;
    User userData;

    EditText userName,email,phone,birthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();
        userID = (String) user.getUid();

        cancel =findViewById(R.id.editProfileCancel);
        update = findViewById(R.id.editProfileUpdateBtn);

        userName = findViewById(R.id.editProfileName);
        phone = findViewById(R.id.editProfilePhone);
        birthDay = findViewById(R.id.editProfileBirthDay);

        mFirebaseDatabase.getReference().child("Store").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userData = dataSnapshot.getValue(User.class);

                userName.setText(userData.getUsername());
                birthDay.setText(userData.getBirthday());
                phone.setText(userData.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase.getReference().child("Store").child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userData = dataSnapshot.getValue(User.class);
                        userData.setUsername(userName.getText().toString());
                        userData.setPhone(phone.getText().toString());
                        userData.setBirthday(birthDay.getText().toString());

                        FirebaseDatabase.getInstance().getReference("Store").child(userData.getId()).setValue(userData);

                        Toast.makeText(EditProfileActivity.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



    }

}
