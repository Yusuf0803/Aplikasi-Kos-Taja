package com.mykostaja.kostaja.Pencari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mykostaja.kostaja.DataUser.DataUser;
import com.mykostaja.kostaja.Login;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.Recycler.RecyclerViewAdapter;
import com.mykostaja.kostaja.DataKost.data_kost1;

import java.util.ArrayList;

public class MainActivity_User extends AppCompatActivity {

    BottomNavigationView nav_bottom;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<data_kost1> datakost1;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    FirebaseAuth Auth;
    FirebaseUser User;
    String IdUser;
    String getNoHp;
    String emailFromDb, userFromDb, phoneFromDb;
    EditText cari_main_user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user);

        getNoHp = getIntent().getStringExtra("noHp");

        nav_bottom = findViewById(R.id.nav_bottom);

        recyclerView = findViewById(R.id.recyclerview_main_user);
        databaseReference = FirebaseDatabase.getInstance().getReference("Kos");
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity_User.this));
        datakost1 = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(datakost1, MainActivity_User.this);
        recyclerView.setAdapter(recyclerViewAdapter);
        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
        IdUser = User.getUid();

        // Memanggil data get data dari firebase
        GetData("");

        cari_main_user = findViewById(R.id.cari_main_user);
        cari_main_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = editable.toString().toLowerCase();
                GetData(searchText);
            }
        });

        // Memfungsikan search berdasarkan ID
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datakost1.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    data_kost1 data_kos = dataSnapshot.getValue(data_kost1.class);
                    datakost1.add(data_kos);
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nav_bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Kos_Saya_user:
                        Toast.makeText(MainActivity_User.this, "Kos Saya", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.Pesan_user:
                        Intent intent1 = new Intent(MainActivity_User.this, chat_user.class);
                        MainActivity_User.this.startActivity(intent1);
                        return true;

                    case R.id.Profil_user:
                        Intent intent = new Intent(MainActivity_User.this, profil_user.class);
                        MainActivity_User.this.startActivity(intent);
                        return true;
                }
                return MainActivity_User.super.onOptionsItemSelected(item);
            }
        });
    }

    private void getUser() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Pencari");
        databaseReference.child(getNoHp).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        emailFromDb = String.valueOf(dataSnapshot.child("email").getValue());
                        phoneFromDb = getNoHp;
                        userFromDb = String.valueOf(dataSnapshot.child("username").getValue());
                        addAuthUser();
                    } else {

                    }
                }
            }
        });
    }

    private void addAuthUser() {
        DataUser user = new DataUser("", userFromDb, phoneFromDb, emailFromDb, "", "");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("AuthPencari");
        databaseReference.child(IdUser).setValue(user);
    }

    private void cekUser() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("AuthPencari");
        databaseReference.child(IdUser).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        // Jika data ditemukan
                    } else {
                        getUser();
                    }
                }
            }
        });
    }

    private void GetData(String searchQuery) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Kos");
        Query query;
        if (searchQuery.isEmpty()) {
            query = databaseReference;
        } else {
            query = databaseReference.orderByChild("namakost");
        }

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datakost1.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    data_kost1 data_kos = dataSnapshot.getValue(data_kost1.class);
                    String namaKost = data_kos.getNamakost();

                    // Memeriksa apakah nama kost mengandung kata yang sesuai dengan pencarian
                    if (namaKost.toLowerCase().contains(searchQuery.toLowerCase())) {
                        datakost1.add(data_kos);
                    }
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Data Gagal Dimuat", Toast.LENGTH_SHORT).show();
                Log.e("MyListActivity", error.getDetails() + " " + error.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cekUser();
    }
}
