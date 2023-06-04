package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText Email_user, Password_user;
    private TextView Daftar_user, Forget_user, panah_user;
    private Button Btn_Login_user;
    String getPassword,getEmail,getUsertype;
    Spinner tipeuser;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener Listener;
    private FirebaseDatabase firebaseDatabase;
    private ProgressBar progressBar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Email_user = findViewById(R.id.Email_user);
        Password_user = findViewById(R.id.Password_user);
        Daftar_user = findViewById(R.id.Daftar_user);
        Forget_user = findViewById(R.id.Forget_user);
        Btn_Login_user = findViewById(R.id.Btn_Login_user);
        panah_user = findViewById(R.id.panah_user);
        tipeuser = findViewById(R.id.tipe_user);
        progressBar = findViewById(R.id.Progressbar_Login);
        progressBar.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && user.isEmailVerified())
                {
                    startActivity(new Intent(Login.this, MainActivity_User.class));

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
                getUsertype=tipeuser.getSelectedItem().toString();
                firebaseDatabase = firebaseDatabase.getReference().getDatabase();

                //Definisi boolean
                boolean a,b;
                a = TextUtils.isEmpty(getEmail);
                b = TextUtils.isEmpty(getPassword);

                //check email+pass = null?
                if (a||b){
                    Toast.makeText(Login.this,"Email or Password can't be Empty !",Toast.LENGTH_LONG).show();
                }else{
                    if (getUsertype.equals("Pencari Kost")){
                        loginuserpencari();
                    }else {
                        loginuserpemilik();
                    }
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

    private void loginuserpencari() {
        auth.signInWithEmailAndPassword(getEmail,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    String uid = task.getResult().getUser().getUid();
                    if (auth.getCurrentUser().isEmailVerified()){
                        FirebaseDatabase.getInstance().getReference(uid).child("User").child("Pencari").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String usertype = snapshot.getValue(String.class);
                                if (usertype != null && usertype.equals("Pencari Kost")){
                                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, MainActivity_Admin.class);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
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
            }
        });
    }

    private void loginuserpemilik() {
        auth.signInWithEmailAndPassword(getEmail,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    String uid = task.getResult().getUser().getUid();
                    if (auth.getCurrentUser().isEmailVerified()){
                        FirebaseDatabase.getInstance().getReference(uid).child("User").child("Pemilik").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String usertype = snapshot.getValue(String.class);
                                if (usertype != null && usertype.equals("Pemilik Kost")){
                                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, MainActivity_Admin.class);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
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
            }
        });
    }
}