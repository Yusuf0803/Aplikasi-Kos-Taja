package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class profil_admin extends AppCompatActivity {

    private ImageView gambar_profil_admin;

    private TextView nama_admin, nohp_admin;

    private TextView akun_profil_admin, pengaturan_profil_admin,riwayat_profil_admin, bantuan_profil_admin, keluar_profil_admin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_admin);

        //untuk profil admin
        gambar_profil_admin = findViewById(R.id.gambar_profil_admin);

        //untuk nama dan nohp admin
        nama_admin = findViewById(R.id.nama_admin);
        nohp_admin = findViewById(R.id.nohp_admin);

        akun_profil_admin = findViewById(R.id.akun_profil_admin);
        pengaturan_profil_admin = findViewById(R.id.pengaturan_profil_admin);
        riwayat_profil_admin = findViewById(R.id.riwayat_profil_admin);
        bantuan_profil_admin = findViewById(R.id.bantuan_profil_admin);
        keluar_profil_admin = findViewById(R.id.keluar_profil_admin);

        akun_profil_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profil_admin.this, "belum dikonfigurasi", Toast.LENGTH_SHORT).show();
            }
        });

        pengaturan_profil_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profil_admin.this, "belum dikonfigurasi", Toast.LENGTH_SHORT).show();
            }
        });

        riwayat_profil_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profil_admin.this, "belum dikonfigurasi", Toast.LENGTH_SHORT).show();
            }
        });

        bantuan_profil_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profil_admin.this, "belum dikonfigurasi", Toast.LENGTH_SHORT).show();
            }
        });

        keluar_profil_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(profil_admin.this,Login.class);
                profil_admin.this.startActivity(intent);
                Toast.makeText(profil_admin.this, "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        return;
    }
}
