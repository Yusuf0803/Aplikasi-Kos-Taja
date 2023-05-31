package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText Email_user, Password_user;
    private TextView Daftar_user, Forget_user, panah_user;
    private Button Btn_Login_user;
    String getPassword,getEmail;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener Listener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email_user = findViewById(R.id.Email_user);
        Password_user = findViewById(R.id.Password_user);
        Daftar_user = findViewById(R.id.Daftar_user);
        Forget_user = findViewById(R.id.Forget_user);
        Btn_Login_user = findViewById(R.id.Btn_Login_user);
        panah_user = findViewById(R.id.panah_user);


        auth = FirebaseAuth.getInstance();

        Listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && user.isEmailVerified())
                {
                    startActivity(new Intent(Login.this, MainActivity.class));

                }
            }
        };




        Daftar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));
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
                startActivity(new Intent(Login.this,LupaPassword.class));
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
                    Toast.makeText(Login.this,"Email or Password can't be Empty !",Toast.LENGTH_LONG).show();
                }else{
                    loginuser();
                }
            }
        });
    }

    protected  void onStart(){
        super.onStart();
        auth.addAuthStateListener(Listener);
    }
    @Override
    protected  void  onStop(){
        super.onStop();
        if (Listener != null){
            auth.removeAuthStateListener(Listener);
        }
    }

    private void loginuser() {
        auth.signInWithEmailAndPassword(getEmail,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Progress_login.setVisibility(View.GONE);
                    if (auth.getCurrentUser().isEmailVerified())
                    {
                        Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
                        alert.setTitle("Periksa Email anda untuk verifikasi");
                        alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                return;
                            }
                        });
                        alert.create();
                        alert.show();
                    }
                }
                else
                {
                    //Progress_login.setVisibility(View.GONE);
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}