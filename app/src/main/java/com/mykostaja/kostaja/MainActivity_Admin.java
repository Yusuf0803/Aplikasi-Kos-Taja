package com.mykostaja.kostaja;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.checkerframework.common.subtyping.qual.Bottom;

public class MainActivity_Admin extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView add, bantuan_main_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin);
        bottomNavigationView = findViewById(R.id.nav_bottom);
        add = findViewById(R.id.Add);
        bantuan_main_admin = findViewById(R.id.bantuan_main_admin);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home_admin:
                        Toast.makeText(MainActivity_Admin.this, "Menu utama Pemilik Kost", Toast.LENGTH_SHORT);
                        return true;
                    case R.id.Pesan_admin:
                        Intent intent2 = new Intent(MainActivity_Admin.this,chat_admin.class);
                        Toast.makeText(MainActivity_Admin.this, "Chat", Toast.LENGTH_SHORT);
                        MainActivity_Admin.this.startActivity(intent2);
                        return true;
                    case R.id.Akun_admin:
                        Intent intent3 = new Intent(MainActivity_Admin.this, profil_admin.class);
                        Toast.makeText(MainActivity_Admin.this, "Profil Admin", Toast.LENGTH_SHORT).show();
                        MainActivity_Admin.this.startActivity(intent3);
                        return true;
                }
                return MainActivity_Admin.super.onOptionsItemSelected(item);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity_Admin.this, Add_datakos.class);
                startActivity(x);

            }
        });

        bantuan_main_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_Admin.this, bantuan_user.class);
                startActivity(i);

            }
        });
    }
}
