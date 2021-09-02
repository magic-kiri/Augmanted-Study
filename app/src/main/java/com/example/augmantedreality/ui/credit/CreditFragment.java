package com.example.augmantedreality.ui.credit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.augmantedreality.R;
import com.google.android.material.snackbar.Snackbar;

public class CreditFragment extends Fragment {

    Button rateUs;
    private CreditViewModel creditViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        creditViewModel =
                ViewModelProviders.of(this).get(CreditViewModel.class);
        View root = inflater.inflate(R.layout.fragment_credit, container, false);
        rateUs = root.findViewById(R.id.ratebutton);

        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thanks for your feedback!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return root;
    }
}