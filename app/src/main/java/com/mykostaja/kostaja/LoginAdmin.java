package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAdmin extends AppCompatActivity {

    private EditText Email_logus, Password_logus;
    private TextView Daftar_logus, Forget_logus, panah_admin;
    private Button Btn_Login_logus;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginadmin);
        Email_logus = findViewById(R.id.Email_admin);
        Password_logus = findViewById(R.id.Password_admin);
        Daftar_logus = findViewById(R.id.Daftar_admin);
        Forget_logus = findViewById(R.id.Forget_admin);
        Btn_Login_logus = findViewById(R.id.Btn_Login_admin);
        panah_admin = findViewById(R.id.panah_admin);

    }
}
