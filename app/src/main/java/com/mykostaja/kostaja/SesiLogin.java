package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SesiLogin extends AppCompatActivity {

    private TextView panah_sesi, user_sesi, admin_sesi, about_sesi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sesilogin);

        panah_sesi = findViewById(R.id.panah_sesi);
        user_sesi = findViewById(R.id.user_sesi);
        admin_sesi = findViewById(R.id.admin_sesi);
        about_sesi = findViewById(R.id.about_sesi);

        user_sesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(SesiLogin.this, LoginUser.class);
                //x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(x);
            }
        });

        admin_sesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(SesiLogin.this, LoginAdmin.class);
                //x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(x);
            }
        });

    }
}
