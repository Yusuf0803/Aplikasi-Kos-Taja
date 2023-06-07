package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity_User extends AppCompatActivity {
    BottomNavigationView nav_bottom;
    ListView mylist;
    ArrayList<String> myarraylist = new ArrayList<>();

    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user);
        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(MainActivity_User.this, android.R.layout.simple_list_item_1,myarraylist);

        nav_bottom = findViewById(R.id.nav_bottom);
        mylist =findViewById(R.id.listView);

        mRef = FirebaseDatabase.getInstance().getReference();
        mylist.setAdapter(myArrayAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                myarraylist.add(value);
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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