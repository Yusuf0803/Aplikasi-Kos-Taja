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

public class LoginUser extends AppCompatActivity {

    private EditText Email_user, Password_user;
    private TextView Daftar_user, Forget_user, panah_user;
    private Button Btn_Login_user;
    String getPassword,getEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginuser);

        Email_user = findViewById(R.id.Email_user);
        Password_user = findViewById(R.id.Password_user);
        Daftar_user = findViewById(R.id.Daftar_user);
        Forget_user = findViewById(R.id.Forget_user);
        Btn_Login_user = findViewById(R.id.Btn_Login_user);
        panah_user = findViewById(R.id.panah_user);

        Daftar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginUser.this,SignupUser.class));
            }
        });

        panah_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Forget_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginUser.this,LupaPassword.class));
            }
        });

        Btn_Login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mendapatkan data user
                getEmail=Email_user.getText().toString();
                getPassword=Password_user.getText().toString();


                //Definisi boolean
                boolean a,b;
                a = TextUtils.isEmpty(getEmail);
                b = TextUtils.isEmpty(getPassword);

                //check email+pass = null?
                if (a||b){
                    Toast.makeText(LoginUser.this,"Email or Password can't be Empty !",Toast.LENGTH_LONG).show();
                }else{

                }
            }
        });
    }
}
