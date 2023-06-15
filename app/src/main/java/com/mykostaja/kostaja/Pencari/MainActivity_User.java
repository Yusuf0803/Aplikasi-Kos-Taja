package com.mykostaja.kostaja.Pencari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.RecyclerViewAdapter;
import com.mykostaja.kostaja.data_kost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_User extends AppCompatActivity {

    BottomNavigationView nav_bottom;

    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<data_kost> datakost;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user);

        nav_bottom = findViewById(R.id.nav_bottom);

        recyclerView = findViewById(R.id.recyclerview_main_user);
        databaseReference =FirebaseDatabase.getInstance().getReference("Kos");
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity_User.this));
        datakost = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(datakost, MainActivity_User.this);
        recyclerView.setAdapter(recyclerViewAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    data_kost data_kos = dataSnapshot.getValue(data_kost.class);
                    datakost.add(data_kos);
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

                switch (item.getItemId()){
                    case R.id.cari_kos_user:
                        Intent cari_kos_user = new Intent(MainActivity_User.this,MainActivity_User.class);
                        Toast.makeText(MainActivity_User.this, "Cari Kos", Toast.LENGTH_SHORT);
                        return true;
                    case R.id.Kos_Saya_user:
                        Toast.makeText(MainActivity_User.this, "Kos Saya", Toast.LENGTH_SHORT);
                        return true;

                    case R.id.Pesan_user:
                        Intent intent1 = new Intent(MainActivity_User.this,chat_user.class);
                        Toast.makeText(MainActivity_User.this, "Chat", Toast.LENGTH_SHORT).show();
                        MainActivity_User.this.startActivity(intent1);
                        return true;

                    case R.id.Profil_user:
                        Intent intent = new Intent(MainActivity_User.this, profil_user.class);
                        Toast.makeText(MainActivity_User.this, "Profil", Toast.LENGTH_SHORT).show();
                        MainActivity_User.this.startActivity(intent);
                        return true;
                }
                return MainActivity_User.super.onOptionsItemSelected(item);
            }
        });

    }

}