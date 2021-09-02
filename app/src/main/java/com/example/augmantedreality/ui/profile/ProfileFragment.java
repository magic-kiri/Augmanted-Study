package com.example.augmantedreality.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.augmantedreality.BasementActivity;
import com.example.augmantedreality.EditProfileActivity;
import com.example.augmantedreality.LogInActivity;
import com.example.augmantedreality.R;
import com.example.augmantedreality.SignUpActivity;
import com.example.augmantedreality.ui.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private TextView edit;
    private TextView name,email,phone,birthDay;
    FirebaseUser user;
    User userData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        user = mAuth.getCurrentUser();
        userID = (String) user.getUid();


        name = root.findViewById(R.id.profileName);
        email = root.findViewById(R.id.profileEmail);
        phone = root.findViewById(R.id.profilePhone);
        birthDay = root.findViewById(R.id.profileBirthDay);
        edit = root.findViewById(R.id.profileEdit);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        mFirebaseDatabase.getReference().child("Store").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userData = dataSnapshot.getValue(User.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        name.setText(userData.getUsername());
                        email.setText(userData.getEmail());
                        phone.setText(userData.getPhone());
                        birthDay.setText(userData.getBirthday());
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return root;
    }
}