package com.mykostaja.kostaja.Pencari;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mykostaja.kostaja.R;

public class edit_profil_user extends AppCompatActivity {

    private TextView panah_edit_profil;
    private EditText nama_edit_user, email_edit_user, nohp_edit_user;
    private Button simpan_edit_user;
    private ImageView gambar_edit_profil;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil_user);

    panah_edit_profil = findViewById(R.id.tv_panah_eduser);
    gambar_edit_profil = findViewById(R.id.iv_gambar_eduser);
    nama_edit_user = findViewById(R.id.ed_nama_eduser);
    email_edit_user = findViewById(R.id.ed_email_eduser);
    nohp_edit_user = findViewById(R.id.ed_nohp_eduser);
    simpan_edit_user = findViewById(R.id.btn_simpan_eduser);

    panah_edit_profil.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    });

    }
}
