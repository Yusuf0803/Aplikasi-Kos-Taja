package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {

    private TextView Masuk_Signup, panah_signup;
    private EditText Re_Password_Signup, Password_Signup, Email_Signup, Nohp_Signup, Nama_Signup;
    private Spinner tipe_user;
    private ProgressBar progressBar;
    private AppCompatButton btn_register;
    private FirebaseAuth auth;
    private String getEmail,getPassword,getUsertype,getPhone,getRepassword,getUsername;
    private FirebaseUser firebaseUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Definisi tipe data & data
        Masuk_Signup = findViewById(R.id.Masuk_Signup);
        panah_signup = findViewById(R.id.panah_signup);
        Re_Password_Signup = findViewById(R.id.Re_Password_Signup);
        Password_Signup = findViewById(R.id.Password_Signup);
        Email_Signup = findViewById(R.id.Email_Signup);
        Nohp_Signup = findViewById(R.id.Nohp_Signup);
        Nama_Signup = findViewById(R.id.Nama_Signup);
        tipe_user = findViewById(R.id.tipe_user);
        progressBar = findViewById(R.id.Progressbar_Signup);
        progressBar.setVisibility(View.GONE);
        btn_register=findViewById(R.id.Btn_Signup);
        auth = FirebaseAuth.getInstance();

        //fungsi button
        panah_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Masuk_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                checkdatauser();
            }
        });
    }

    private void checkdatauser() {
        getUsertype = tipe_user.getSelectedItem().toString();
        getUsername = Nama_Signup.getText().toString();
        getEmail = Email_Signup.getText().toString();
        getPhone = Nohp_Signup.getText().toString();
        getPassword = Password_Signup.getText().toString();
        getRepassword = Re_Password_Signup.getText().toString();

        boolean a,b,c,d,e;
        a = TextUtils.isEmpty(getUsername);
        b = TextUtils.isEmpty(getEmail);
        c = TextUtils.isEmpty(getPassword);
        d = TextUtils.isEmpty(getPhone);
        e = TextUtils.isEmpty(getRepassword);



        if (a||b||c||d||e){
            Toast.makeText(Signup.this,"Masih tedapat data yang kosong !",Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }else if (!getPassword.equals(getRepassword)) {
            Toast.makeText(Signup.this, "Password yang anda masukkan tidak sesuai dengan Password awal !", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        } else if (getPassword.length()<8 && getRepassword.length()<8) {
            Toast.makeText(getApplicationContext(), "Password Minimal 8 Karakter", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        } else {
            createUserAccount();
        }
    }

    //Membuat UserAccount
    private void createUserAccount() {

        auth.createUserWithEmailAndPassword(getEmail,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                User pemilik = new User (getUsertype,getUsername,getPhone,getEmail,getPassword,getRepassword);
                FirebaseDatabase.getInstance().getReference("User").setValue(pemilik).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //cek status keberhasilan saat mendaftarkan email
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Signup.this,"Registrasi Berhasil !!, Please check your email verification",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Signup.this,Login.class));
                                        finish();
                                    }else{
                                        Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}