package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignupAdmin extends AppCompatActivity {

    private TextView Masuk_Signup_Admin, panah_signup_admin;
    private EditText Re_Password_Signup_Admin, Password_Signup_Admin, Email_Signup_Admin, Nohp_Signup_Admin, Nama_Admin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupadmin);

        Masuk_Signup_Admin = findViewById(R.id.Masuk_Signup_Admin);
        panah_signup_admin = findViewById(R.id.panah_signup_admin);
        Re_Password_Signup_Admin = findViewById(R.id.Re_Password_Signup_Admin);
        Password_Signup_Admin = findViewById(R.id.Password_Signup_Admin);
        Email_Signup_Admin = findViewById(R.id.Email_Signup_Admin);
        Nohp_Signup_Admin = findViewById(R.id.Nohp_Signup_Admin);
        Nama_Admin = findViewById(R.id.Nama_Admin);

        Masuk_Signup_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupAdmin.this,LoginAdmin.class));
            }
        });

        panah_signup_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}