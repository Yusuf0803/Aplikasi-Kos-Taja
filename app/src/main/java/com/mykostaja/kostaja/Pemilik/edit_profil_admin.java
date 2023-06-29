package com.mykostaja.kostaja.Pemilik;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mykostaja.kostaja.R;

public class edit_profil_admin extends AppCompatActivity {

    private TextView panah_edit_profil;
    private EditText nama_edit_user, email_edit_user, nohp_edit_user;
    private Button simpan_edit_user;
    private ImageView gambar_edit_profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil_admin);

        panah_edit_profil = findViewById(R.id.tv_panah_edadmin);
        gambar_edit_profil = findViewById(R.id.iv_gambar_edadmin);
        nama_edit_user = findViewById(R.id.ed_nama_edadmin);
        email_edit_user = findViewById(R.id.ed_email_edadmin);
        nohp_edit_user = findViewById(R.id.ed_nohp_edadmin);
        simpan_edit_user = findViewById(R.id.btn_simpan_edadmin);

        panah_edit_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
