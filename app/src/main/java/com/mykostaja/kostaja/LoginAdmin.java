package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAdmin extends AppCompatActivity {

    private EditText Email_Admin, Password_Admin;
    private TextView Daftar_Admin, Forget_Admin, panah_admin;
    private Button Btn_Login_Admin;
    String getEmail,getPassword;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginadmin);
        Email_Admin = findViewById(R.id.Email_Admin);
        Password_Admin = findViewById(R.id.Password_Admin);
        Daftar_Admin = findViewById(R.id.Daftar_Admin);
        Forget_Admin = findViewById(R.id.Forget_Admin);
        Btn_Login_Admin = findViewById(R.id.Btn_Login_Admin);
        panah_admin = findViewById(R.id.panah_admin);

        Daftar_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginAdmin.this,SignupAdmin.class));
            }
        });

        panah_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

       Forget_Admin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(LoginAdmin.this,LupaPassword.class));
           }
       });

       Btn_Login_Admin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //mendapatkan data user
               getEmail=Email_Admin.getText().toString();
               getPassword=Password_Admin.getText().toString();

               //Definisi boolean
               boolean a,b;
               a = TextUtils.isEmpty(getEmail);
               b = TextUtils.isEmpty(getPassword);

               //check email+password
               if (a||b){
                   Toast.makeText(LoginAdmin.this,"Email or Password can't be Empty !",Toast.LENGTH_LONG).show();
               }else{
                   ;
               }
           }
       });

    }
}
