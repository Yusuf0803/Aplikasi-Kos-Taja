package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity_User extends AppCompatActivity {
    BottomNavigationView nav_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user);
        nav_bottom = findViewById(R.id.nav_bottom);
        nav_bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.cari_kos_user:
                        Toast.makeText(MainActivity_User.this, "Cari Kos", Toast.LENGTH_SHORT);
                        return true;
                    case R.id.Kos_Saya_user:
                        Toast.makeText(MainActivity_User.this, "Kos Saya", Toast.LENGTH_SHORT);
                        return true;

                    case R.id.Pesan_user:
                        Toast.makeText(MainActivity_User.this, "Chat", Toast.LENGTH_SHORT);
                        return true;
                    case R.id.Profil_user:
                        Intent intent = new Intent(MainActivity_User.this, profil_user.class);
                        MainActivity_User.this.startActivity(intent);
                }
                return MainActivity_User.super.onOptionsItemSelected(item);
            }
        });
    }
}