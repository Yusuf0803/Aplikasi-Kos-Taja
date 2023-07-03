package com.mykostaja.kostaja.Pemilik;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mykostaja.kostaja.AddDataKost.Add_datakos1;
import com.mykostaja.kostaja.DataUser.DataUser;
import com.mykostaja.kostaja.Pencari.bantuan_user;
import com.mykostaja.kostaja.R;

import java.util.ArrayList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity_Admin extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    ImageView iv_gambar_admin;
    TextView tv_nama_admin;
    TextView add,viewdata; //button
    TextView nama_main_admin;

    FirebaseAuth Auth;
    FirebaseUser User;
    String IdUser;
    String getNoHp;
    String emailFromDb, userFromDb, phoneFromDb,passwordFromDb;
    private AdView mAdView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getNoHp = getIntent().getStringExtra("noHpAdmin");
        nama_main_admin = findViewById(R.id.nama_main_admin);

        bottomNavigationView = findViewById(R.id.nav_bottom);

        iv_gambar_admin = findViewById(R.id.gambar_main_admin);
        tv_nama_admin = findViewById(R.id.nama_main_admin);

        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
        IdUser = User.getUid();

        add = findViewById(R.id.Add);
        viewdata= findViewById(R.id.view_data_kost);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home_admin:
                        Toast.makeText(MainActivity_Admin.this, "Menu utama Pemilik Kost", Toast.LENGTH_SHORT);
                        return true;
                    case R.id.Pesan_admin:
                        Intent intent2 = new Intent(MainActivity_Admin.this,chat_admin.class);
                        Toast.makeText(MainActivity_Admin.this, "Chat", Toast.LENGTH_SHORT);
                        MainActivity_Admin.this.startActivity(intent2);
                        return true;
                    case R.id.Akun_admin:
                        Intent intent3 = new Intent(MainActivity_Admin.this, profil_admin.class);
                        Toast.makeText(MainActivity_Admin.this, "Profil Admin", Toast.LENGTH_SHORT).show();
                        MainActivity_Admin.this.startActivity(intent3);
                        return true;
                }
                return MainActivity_Admin.super.onOptionsItemSelected(item);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity_Admin.this, Add_datakos1.class);
                startActivity(x);

            }
        });

        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_Admin.this, view_list_kost.class);
                startActivity(i);
            }
        });
    }

    private void getUser(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Pemilik");
        databaseReference.child(getNoHp).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        emailFromDb = String.valueOf(dataSnapshot.child("email").getValue());
                        phoneFromDb = getNoHp;
                        userFromDb = String.valueOf(dataSnapshot.child("username").getValue());
                        passwordFromDb = String.valueOf(dataSnapshot.child("password").getValue());
                        nama_main_admin.setText(userFromDb);
                        addAuthUser();
                    } else {

                    }
                }
            }
        });
    }
    private void addAuthUser(){
        DataUser user = new DataUser("",userFromDb,phoneFromDb,emailFromDb,passwordFromDb,"");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("AuthPemilik");
        databaseReference.child(IdUser).setValue(user);
    }
    private void cekUser(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("AuthPemilik");
        databaseReference.child(IdUser).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        // jika data ditemukan
                        DataSnapshot dataSnapshot = task.getResult();
                        String username = String.valueOf(dataSnapshot.child("username").getValue());
                        nama_main_admin.setText(username);
                    } else {
                        getUser();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cekUser();
    }
}
