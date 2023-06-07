package com.mykostaja.kostaja;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.checkerframework.common.subtyping.qual.Bottom;

public class MainActivity_Admin extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin);
        bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home_admin:
                        Intent x = new Intent(MainActivity_Admin.this,MainActivity_Admin.class);
                        Toast.makeText(MainActivity_Admin.this, "Menu utama Pemilik Kost", Toast.LENGTH_SHORT);
                        MainActivity_Admin.this.startActivity(x);
                        return true;

                    case R.id.Pesan_admin:
                        Intent x = new Intent(MainActivity_Admin.this,chat_user.class);
                        Toast.makeText(MainActivity_Admin.this, "Chat", Toast.LENGTH_SHORT);
                        MainActivity_Admin.this.startActivity(x);
                    case R.id.Profil_user:
                        Intent x = new Intent(MainActivity_Admin.this, profil_admin.class);
                        Toast.makeText(MainActivity_Admin.this, "Profil akun", Toast.LENGTH_SHORT).show();
                        MainActivity_Admin.this.startActivity(x);
                }
                return MainActivity_Admin.super.onOptionsItemSelected(item);
            }
        });
    }
}
