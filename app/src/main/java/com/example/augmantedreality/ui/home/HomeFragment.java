package com.example.augmantedreality.ui.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.augmantedreality.BasementActivity;
import com.example.augmantedreality.R;
import com.example.augmantedreality.Transition;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ImageView  biologybtn = root.findViewById(R.id.homeBiologyButton);
        final ImageView  physicsbtn = root.findViewById(R.id.homePhysicsButton);
        final ImageView  chemistrybtn = root.findViewById(R.id.homeChemistryButton);
        biologybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Transition.class));
            }
        });
        chemistrybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "This feature will be added in the next virsion!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        physicsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "This feature will be added in the next virsion!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        return root;
    }
}