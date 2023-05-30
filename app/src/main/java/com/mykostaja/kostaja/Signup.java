package com.mykostaja.kostaja;

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
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {

    private TextView Masuk_Signup_User, panah_signup_user;
    private EditText Re_Password_Signup_User, Password_Signup_User, Email_Signup_User, Nohp_Signup_User, Nama_User;
    private Spinner tipe_user;
    private ProgressBar progressBar;
    private AppCompatButton btn_register;
    private FirebaseAuth auth;
    private String getEmail,getPassword,getUsertype,getPhone,getRepassword,getUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Definisi tipe data & data
        Masuk_Signup_User = findViewById(R.id.Masuk_Signup_User);
        panah_signup_user = findViewById(R.id.panah_signup_user);
        Re_Password_Signup_User = findViewById(R.id.Re_Password_Signup_User);
        Password_Signup_User = findViewById(R.id.Password_Signup_User);
        Email_Signup_User = findViewById(R.id.Email_Signup_User);
        Nohp_Signup_User = findViewById(R.id.Nohp_Signup_User);
        Nama_User = findViewById(R.id.Nama_User);
        tipe_user = findViewById(R.id.tipe_user);
        progressBar = findViewById(R.id.progressbarusersignup);
        progressBar.setVisibility(View.GONE);
        btn_register=findViewById(R.id.Btn_Signup_User);

        //fungsi button
        panah_signup_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Masuk_Signup_User.setOnClickListener(new View.OnClickListener() {
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
        getUsername = Nama_User.getText().toString();
        getEmail = Email_Signup_User.getText().toString();
        getPhone = Nohp_Signup_User.getText().toString();
        getPassword = Password_Signup_User.getText().toString();
        getRepassword = Re_Password_Signup_User.getText().toString();

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
        }else {
           createUserAccount();
        }
    }

    //Membuat UserAccount
    private void createUserAccount() {
        auth.createUserWithEmailAndPassword(getEmail,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                User user = new User (getUsertype,getUsername,getPhone,getEmail,getPassword,getRepassword);
                FirebaseDatabase.getInstance().getReference("User").child(auth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //cek status keberhasilan saat mendaftarkan email
                        if (task.isSuccessful()){progressBar.setVisibility(View.GONE);
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