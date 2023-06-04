package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPassword extends AppCompatActivity {

    private EditText Email_Verifikasi;
    private TextView panah_lupapswd;
    private Button Btn_Verifikasi;
    private String email;

    private FirebaseAuth auth;
    //private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lupapassword);

        panah_lupapswd = findViewById(R.id.panah_lupapswd);
        Email_Verifikasi = findViewById(R.id.Email_Verifikasi);
        Btn_Verifikasi = findViewById(R.id.Btn_Verifikasi);
        //auth = FirebaseAuth.getInstance();

        auth = FirebaseAuth.getInstance();

        panah_lupapswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Fungsi verif
        Btn_Verifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }

            private void validateData() {
                email = Email_Verifikasi.getText().toString();

                boolean a;
                a = email.isEmpty();

                if ((a)){
                    Email_Verifikasi.setError("Email can't be empty !!");
                }else {

                    Forgetpass();

                }
            }

            private  void Forgetpass (){
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LupaPassword.this, "Cek Email Anda", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LupaPassword.this,Login.class));
                            finish();
                        }else {
                            Toast.makeText(LupaPassword.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
}
