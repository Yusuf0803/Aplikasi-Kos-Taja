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

import com.google.firebase.auth.FirebaseAuth;
import com.mykostaja.kostaja.Login;
import com.mykostaja.kostaja.Pemilik.profil_admin;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.edit_profil;

public class profil_user extends AppCompatActivity {

    private CardView profil_user;
    private ImageView gambar_profil_user;

    private TextView nama_profil_user, edit_profil_user;
    private TextView pengaturan_profil_user,riwayat_profil_user,bantuan_profil_user,keluar_profil_user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_user);

        gambar_profil_user = findViewById(R.id.gambar_profil_user);

        nama_profil_user = findViewById(R.id.nama_profil_user);
        edit_profil_user = findViewById(R.id.edit_profil_user);

        pengaturan_profil_user = findViewById(R.id.pengaturan_profil_user);
        riwayat_profil_user = findViewById(R.id.riwayat_profil_user);
        bantuan_profil_user = findViewById(R.id.bantuan_profil_user);
        keluar_profil_user = findViewById(R.id.keluar_profil_user);

        edit_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profil_user.this, edit_profil.class);
                startActivity(intent);
            }
        });

        pengaturan_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profil_user.this, "Belum di konfigurasi", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        riwayat_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profil_user.this, "Belum di konfigurasi", Toast.LENGTH_SHORT).show();
                return;
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
}

