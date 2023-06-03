package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
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
                    case R.id.cari_kos:
                        Toast.makeText(MainActivity.this, "Cari Kos", Toast.LENGTH_SHORT);
                        break;
                    case R.id.Kos_Saya:
                        Toast.makeText(MainActivity.this, "Kos Saya", Toast.LENGTH_SHORT);
                        break;

                    case R.id.Pesan:
                        Toast.makeText(MainActivity.this, "Chat", Toast.LENGTH_SHORT);
                        break;
                    case R.id.Profil:
                        Toast.makeText(MainActivity.this, "Profil", Toast.LENGTH_SHORT);
                        break;
                    default:
                }
                return true;
            }
        });
    }
}