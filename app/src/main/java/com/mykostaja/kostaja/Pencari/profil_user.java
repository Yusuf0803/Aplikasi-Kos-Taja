package com.mykostaja.kostaja.Pencari;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mykostaja.kostaja.Login;
import com.mykostaja.kostaja.Pemilik.profil_admin;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.User;
import com.mykostaja.kostaja.edit_profil;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.BreakIterator;

public class profil_user extends AppCompatActivity {

    private CardView profil_user;
    private ImageView gambar_profil_user;

    String getPhone,getUserame;
    private TextView nama_profil_user,info_profil_user,nohp_user;
    private TextView bantuan_profil_user,keluar_profil_user;

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
        
//        getUser("getHp");
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser != null) {
//            String uid = currentUser.getUid();
//            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User").child("Pencari").child(uid);
//            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()) {
//                        String nama = snapshot.child("username").getValue(String.class);
//                        String noHp = snapshot.child("phone").getValue(String.class);
//
//                        nama_profil_user.setText(nama);
//                        nohp_user.setText(noHp);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Error handling
//                }
//            });
//        }




        info_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profil_user.this, edit_profil.class);
                startActivity(intent);
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

//    private void getUser(String Hp) {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Pencari");
//        databaseReference.child(Hp).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@androidx.annotation.NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    if (task.getResult().exists()) {
//                        DataSnapshot dataSnapshot = task.getResult();
//                         = String.valueOf(dataSnapshot.child("email").getValue());
//                    } else {
//                        Toast.makeText(Login.this, "Akun Pencari Tidak Ada", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
}

