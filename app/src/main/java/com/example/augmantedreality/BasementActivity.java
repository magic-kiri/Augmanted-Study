package com.example.augmantedreality;

import android.content.ClipData;
import android.os.Bundle;

import com.example.augmantedreality.ui.User;
import com.example.augmantedreality.ui.performance.PerfomanceFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BasementActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ClipData.Item logOut;

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
    int totalScore;
    TextView nav_email;
    FirebaseUser user;
    User userData;
    Button ratebtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basement);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        user = mAuth.getCurrentUser();
        assert user != null;
        userID = (String) user.getUid();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Currently,you have no massege!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        View header = navigationView.getHeaderView(0);

        avatar = header.findViewById(R.id.nav_avatar);
        nav_username = header.findViewById(R.id.nav_username);
        nav_email = header.findViewById(R.id.nav_email);

        mFirebaseDatabase.getReference().child("Store").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userData = dataSnapshot.getValue(User.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nav_username.setText(userData.getUsername());
                        nav_email.setText(userData.getEmail());

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile,
                R.id.nav_perfomance, R.id.nav_about, R.id.nav_logOut)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        graph();
    }

    private void graph() {
        myRef = mFirebaseDatabase.getReference().child("Store").child(userID);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User account;

                account = dataSnapshot.getValue(User.class);
                tmpUser = account;

                barEntries = new ArrayList<>();
                barEntries.add(new BarEntry(account.getChap1(), 0));
                barEntries.add(new BarEntry(account.getChap2(), 1));
                barEntries.add(new BarEntry(account.getChap3(), 2));
                barEntries.add(new BarEntry(account.getChap4(), 3));
                barEntries.add(new BarEntry(account.getChap5(), 4));

                theDates = new ArrayList<>();
                theDates.add("Chapter 1");
                theDates.add("Chapter 2");
                theDates.add("Chapter 3");
                theDates.add("Chapter 4");
                theDates.add("Chapter 5");
                totalScore = tmpUser.getTotalMarks();
                barDataSet = new BarDataSet(barEntries, "Chapters");

                PerfomanceFragment.totalMark = totalScore;
                PerfomanceFragment.theData = new BarData(theDates, barDataSet);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.basement, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
