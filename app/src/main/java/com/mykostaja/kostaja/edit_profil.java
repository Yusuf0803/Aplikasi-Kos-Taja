package com.mykostaja.kostaja;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class edit_profil extends AppCompatActivity {

    private TextView panah_edit_profil;
    CardView cardView;
    private EditText nama_edit_user, email_edit_user, nohp_edit_user;
    private Button simpan_edit_user;
    private ImageView gambar_edit_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil_user);

    panah_edit_profil = findViewById(R.id.panah_edit_profil);
    cardView = findViewById(R.id.cardview);
    gambar_edit_profil = findViewById(R.id.gambar_edit_profil);
    nama_edit_user = findViewById(R.id.nama_edit_profil);
    email_edit_user = findViewById(R.id.email_edit_profil);
    nohp_edit_user = findViewById(R.id.nohp_edit_profil);
    simpan_edit_user = findViewById(R.id.simpan_edit_profil);

    panah_edit_profil.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    });

    }
}
