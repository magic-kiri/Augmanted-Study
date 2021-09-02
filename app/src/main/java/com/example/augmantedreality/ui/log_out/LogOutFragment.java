package com.example.augmantedreality.ui.log_out;

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
import com.example.augmantedreality.LogInActivity;
import com.example.augmantedreality.MainActivity;
import com.example.augmantedreality.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutFragment extends Fragment {

    private LogOutViewModel logOutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModel =
                ViewModelProviders.of(this).get(LogOutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_log_out, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        logOutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                startActivity(new Intent(getActivity(),MainActivity.class));
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "You have logged out!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                //startActivity(new Intent(BasementActivity.this, MainActivity.class));
            }
        });
        return root;
    }
}