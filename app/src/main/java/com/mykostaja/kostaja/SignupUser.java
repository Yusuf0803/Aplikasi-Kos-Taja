package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignupUser extends AppCompatActivity {

    private TextView Masuk_Signup_User, panah_signup_user;
    private EditText Re_Password_Signup_User, Password_Signup_User, Email_Signup_User, Nohp_Signup_User, Nama_User;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupuser);

        Masuk_Signup_User = findViewById(R.id.Masuk_Signup_User);
        panah_signup_user = findViewById(R.id.panah_signup_user);
        Re_Password_Signup_User = findViewById(R.id.Re_Password_Signup_User);
        Password_Signup_User = findViewById(R.id.Password_Signup_User);
        Email_Signup_User = findViewById(R.id.Email_Signup_User);
        Nohp_Signup_User = findViewById(R.id.Nohp_Signup_User);
        Nama_User = findViewById(R.id.Nama_User);

        Masuk_Signup_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupUser.this,LoginUser.class));
            }
        });

        panah_signup_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
