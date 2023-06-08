package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.firebase.auth.FirebaseAuth;

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
                Intent intent = new Intent(profil_user.this,edit_profil.class);
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
                Intent intent = new Intent(com.mykostaja.kostaja.profil_user.this,bantuan_user.class);
                startActivity(intent);
            }
        });

        keluar_profil_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(profil_user.this, Login.class);
                profil_user.this.startActivity(intent);
                Toast.makeText(profil_user.this, "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
