package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText Nohp_user, Password_user;
    private TextView Daftar_user, Forget_user, panah_user;
    private Button Btn_Login_user;
    String getPassword,getHp,getUsertype;
    String emailFromDb;
    Spinner tipeuser;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener Listener, userAuthListener, adminAuthListener, userListener, adminlistener;
    private FirebaseDatabase firebaseDatabase;
    private ProgressBar progressBar;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context contextLogin;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Nohp_user = findViewById(R.id.Nohp_user);
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

        contextLogin = this;


        sharedPreferences  = getSharedPreferences("UserAuth", Context.MODE_PRIVATE);

        userListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    startActivity(new Intent(Login.this, MainActivity_User.class));
                    Toast.makeText(Login.this, "Login Sebagai Pencari", Toast.LENGTH_SHORT).show();
                    finish();

            }
        };
        userAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (! user.isEmailVerified()){
                    Toast.makeText(Login.this, "Email Belum Diverifikasi", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(Login.this, MainActivity_User.class));
                    Toast.makeText(Login.this, "Login Sebagai Pencari", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        };


        adminAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (! user.isEmailVerified()){
                    Toast.makeText(Login.this, "Email Belum Diverifikasi", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(Login.this, MainActivity_Admin.class));
                    Toast.makeText(Login.this, "Login Sebagai Pemilik", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        };

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
                getHp = Nohp_user.getText().toString();
                getPassword=Password_user.getText().toString();
                getUsertype=tipeuser.getSelectedItem().toString();
                firebaseDatabase = firebaseDatabase.getReference().getDatabase();

                //Definisi boolean
                boolean a,b;
                a = TextUtils.isEmpty(getHp);
                b = TextUtils.isEmpty(getPassword);

                //check email+pass = null?
                if (a||b){
                    Toast.makeText(Login.this,"Email or Password can't be Empty !",Toast.LENGTH_LONG).show();
                }else{
                    if (getUsertype.equals("Pencari Kost")){
                        getUser(getHp);
                    }else {
                        getAdmin(getHp);
                    }
                }
            }
        });
    }

    protected  void onStart(){
        super.onStart();
        if (sharedPreferences.getBoolean("userLogin",false)==true){
            auth.addAuthStateListener(adminAuthListener);
        }else if (sharedPreferences.getString("userToken", null) != null){
            auth.addAuthStateListener(userAuthListener);
        }else {
            
        }
    }

    @Override
    protected  void  onStop(){
        super.onStop();
        if (adminAuthListener != null){
            auth.removeAuthStateListener(adminAuthListener);
        }else if (userAuthListener != null){
            auth.removeAuthStateListener(userAuthListener);
        }
    }

    private void getUser(String Hp){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Pencari");
        databaseReference.child(Hp).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        emailFromDb = String.valueOf(dataSnapshot.child("email").getValue());
                        loginuserpencari();
                    }else {
                        Toast.makeText(Login.this, "Akun Pencari Tidak Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void loginuserpencari() {
        auth.signInWithEmailAndPassword(emailFromDb,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    auth.getCurrentUser().getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<GetTokenResult> task) {
//                            editor = sharedPreferences.edit();
//                            editor.putString("userToken", task.getResult().getToken());
//                            editor.apply();
//                        }
//                    });
//                    auth.addAuthStateListener(userAuthListener);
//                }else {
//                    Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
//                }
                editor = sharedPreferences.edit();
                editor.putBoolean("userLogin", true);
                editor.apply();

            }
        });
    }

    private void getAdmin(String Hp){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Pemilik");
        databaseReference.child(Hp).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        emailFromDb = String.valueOf(dataSnapshot.child("email").getValue());
                        loginuserpemilik();
                    }else {
                        Toast.makeText(Login.this, "Akun Pemilik Tidak Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void loginuserpemilik() {
        auth.signInWithEmailAndPassword(emailFromDb,getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    auth.getCurrentUser().getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            editor = sharedPreferences.edit();
                            editor.putString("adminToken", task.getResult().getToken());
                            editor.apply();
                        }
                    });
                    auth.addAuthStateListener(adminAuthListener);
                }else {
                    Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void hapusPrefUser(String user){
        editor = sharedPreferences.edit();
        editor.remove(user);
        editor.apply();
    }

    public void hapusPrefAdmin(Context context, String admin){
        sharedPreferences = context.getSharedPreferences("UserAuth",context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        editor2.remove(admin);
        editor2.apply();
    }
}

//FirebaseDatabase.getInstance().getReference().child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        if (snapshot.exists()) {
//        String email = snapshot.child("email").getValue(String.class);
//        String password = snapshot.child("password").getValue(String.class);
//
//        // Lakukan validasi login
//        if (inputEmail.equals(email) && inputPassword.equals(password)) {
//        // Login berhasil
//        Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
//        // Lanjutkan ke aktivitas berikutnya
//        } else {
//        // Login gagal
//        Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
//        }
//        } else {
//        // User tidak ditemukan
//        Toast.makeText(LoginActivity.this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
//        }
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        // Handle error
//        }
//        });
