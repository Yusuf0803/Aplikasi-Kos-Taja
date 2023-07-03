package com.mykostaja.kostaja.Pencari;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mykostaja.kostaja.Login;
import com.mykostaja.kostaja.R;

public class profil_user extends AppCompatActivity {
    private ImageView gambar_profil_user;

    String getNoHp,getUserame;
    private TextView nama_profil_user,nohp_user;
    private TextView bantuan_profil_user,keluar_profil_user,info_profil_user;
    private FirebaseAuth Auth;
    private FirebaseUser User;
    private String IdUser;

    private RewardedAd rewardedAd;
    private final String TAG = "MainActivity";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_user);

        gambar_profil_user = findViewById(R.id.gambar_profil_user);

        nama_profil_user = findViewById(R.id.nama_profil_user);
        info_profil_user = findViewById(R.id.akun_profil_user);
        nohp_user = findViewById(R.id.nohp_user);
        bantuan_profil_user = findViewById(R.id.bantuan_profil_user);
        keluar_profil_user = findViewById(R.id.keluar_profil_user);

        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
        IdUser = User.getUid();

        getSetDataUser();

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.toString());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        Log.d(TAG, "Ad was loaded.");
                    }
                });

        info_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = profil_user.this;
                    rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
                            Log.d(TAG, "The user earned the reward.");
                            startActivity(new Intent(profil_user.this,edit_profil_user.class));
//                            int rewardAmount = rewardItem.getAmount();
//                            String rewardType = rewardItem.getType();
                        }
                    });
                } else {
                    Log.d(TAG, "The rewarded ad wasn't ready yet.");
                }
            }
        });
        bantuan_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.mykostaja.kostaja.Pencari.profil_user.this,bantuan_user.class);
                startActivity(intent);
            }
        });

        keluar_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(profil_user.this);
                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah Anda yakin ingin keluar?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Perform the exit action here
                        FirebaseAuth.getInstance().signOut();
                        Intent x = new Intent(profil_user.this, Login.class);
                        // set the new task and clear flags
                        x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(x);
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Continue with the app
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void getSetDataUser(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("AuthPencari");
        databaseReference.child(IdUser).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        nama_profil_user.setText(String.valueOf(dataSnapshot.child("username").getValue()));
                        nohp_user.setText(String.valueOf(dataSnapshot.child("phone").getValue()));
                    } else {

                    }
                }
            }
        });
    }
}

