package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Signup extends AppCompatActivity {

    private TextView Masuk_Signup_User, panah_signup_user;
    private EditText Re_Password_Signup_User, Password_Signup_User, Email_Signup_User, Nohp_Signup_User, Nama_User;
    private Spinner tipe_user;
    private ProgressBar progressBar;
    private AppCompatButton btn_register;
    private String getemail,getpassword,getusertype,getnomerhp,getrepassword,getusername;

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
        getusertype = tipe_user.getSelectedItem().toString();
        getusername = Nama_User.getText().toString();
        getemail = Email_Signup_User.getText().toString();
        getnomerhp = Nohp_Signup_User.getText().toString();
        getpassword = Password_Signup_User.getText().toString();
        getrepassword = Re_Password_Signup_User.getText().toString();

        boolean a,b,c,d,e;
        a = TextUtils.isEmpty(getusername);
        b = TextUtils.isEmpty(getemail);
        c = TextUtils.isEmpty(getpassword);
        d = TextUtils.isEmpty(getnomerhp);
        e = TextUtils.isEmpty(getrepassword);

        if (!getpassword.equals(getrepassword)){
            Toast.makeText(Signup.this,"Password yang anda masukkan tidak sesuai dengan Password awal !",Toast.LENGTH_SHORT).show();
        }

        if (a||b||c||d||e){
            Toast.makeText(Signup.this,"Masih tedapat data yang kosong !",Toast.LENGTH_SHORT).show();
        }else {
            //createUserAccount();
        }
    }

    //Membuat UserAccount
    /*private void createUserAccount() {
        auth.createUserWithEmailAndPassword(getEmail,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                Pengguna user = new Pengguna (getEmail,getPassword);
                FirebaseDatabase.getInstance().getReference("Pengguna").setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //cek status keberhasilan saat mendaftarkan email
                        if (task.isSuccessful()){progressBar.setVisibility(View.GONE);
                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this,"Registrasi Berhasil !!, Please check your email verification",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                        finish();
                                    }else{
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(SignupActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SignupActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }*/
}