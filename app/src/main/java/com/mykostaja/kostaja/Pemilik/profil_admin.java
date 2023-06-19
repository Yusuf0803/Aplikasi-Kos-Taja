package com.mykostaja.kostaja.Pemilik;

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

import com.google.firebase.auth.FirebaseAuth;
import com.mykostaja.kostaja.Login;
import com.mykostaja.kostaja.Pencari.profil_user;
import com.mykostaja.kostaja.R;

public class profil_admin extends AppCompatActivity {

    private ImageView gambar_profil_admin;

    private TextView nama_admin, nohp_admin;

    private TextView akun_profil_admin, bantuan_profil_admin, keluar_profil_admin;

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
        bantuan_profil_admin = findViewById(R.id.bantuan_profil_admin);
        keluar_profil_admin = findViewById(R.id.keluar_profil_admin);

        akun_profil_admin.setOnClickListener(new View.OnClickListener() {
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
                AlertDialog.Builder builder = new AlertDialog.Builder(profil_admin.this);
                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah Anda yakin ingin keluar?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Perform the exit action here
                        FirebaseAuth.getInstance().signOut();
                        Intent x = new Intent(profil_admin.this, Login.class);
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
