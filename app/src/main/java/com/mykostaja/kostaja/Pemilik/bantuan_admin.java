package com.mykostaja.kostaja.Pemilik;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mykostaja.kostaja.Pencari.bantuan_user;
import com.mykostaja.kostaja.Pencari.pusatbantuan;
import com.mykostaja.kostaja.R;

public class bantuan_admin extends AppCompatActivity {

    private TextView panah_bantuan_admin, WA_admin, pusat_bantuan_admin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantuan_admin);

        panah_bantuan_admin = findViewById(R.id.panah_bantuan_admin);
        WA_admin = findViewById(R.id.WA_admin);
        pusat_bantuan_admin = findViewById(R.id.pusat_bantuan_admin);

        panah_bantuan_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        pusat_bantuan_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bantuan_admin.this, pusatbantuan.class));
            }
        });
        WA_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ganti "1234567890" dengan nomor WhatsApp Anda
                String phoneNumber = "+628562775756";

                // Membuat URI untuk mengarahkan ke aplikasi WhatsApp
                Uri uri = Uri.parse("whatsapp://send?phone=" + phoneNumber);

                // Membuat intent untuk membuka aplikasi WhatsApp
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                // Memeriksa apakah ada aplikasi WhatsApp di perangkat pengguna
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Menjalankan intent
                    startActivity(intent);
                } else {
                    // Jika tidak ada aplikasi WhatsApp, Anda dapat memberikan tindakan alternatif
                    // misalnya, membuka tautan WhatsApp menggunakan browser web
                    Uri webUri = Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                    startActivity(webIntent);
                }
            }
        });
    }
}
