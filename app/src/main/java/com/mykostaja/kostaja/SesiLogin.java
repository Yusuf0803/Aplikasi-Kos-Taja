package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SesiLogin extends AppCompatActivity {

    private TextView panah_sesi,about_sesi;
    private Button Btn_Sesi_User, Btn_Sesi_Admin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sesilogin);

        panah_sesi = findViewById(R.id.panah_sesi);
        Btn_Sesi_User = findViewById(R.id.Btn_Sesi_User);
        Btn_Sesi_Admin = findViewById(R.id.Btn_Sesi_Admin);
        about_sesi = findViewById(R.id.about_sesi);

        Btn_Sesi_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(SesiLogin.this, Login.class);
                //x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(x);
            }
        });

        Btn_Sesi_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(SesiLogin.this, LoginAdmin.class);
                //x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(x);
            }
        });

        panah_sesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
