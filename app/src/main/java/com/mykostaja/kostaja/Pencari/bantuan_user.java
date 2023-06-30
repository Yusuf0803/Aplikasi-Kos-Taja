package com.mykostaja.kostaja.Pencari;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mykostaja.kostaja.R;

public class bantuan_user extends AppCompatActivity {
    private TextView panah_bantuan_user, WA_user, pusat_bantuan_user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantuan_user);

        panah_bantuan_user = findViewById(R.id.panah_bantuan_user);
        WA_user = findViewById(R.id.WA_user);
        pusat_bantuan_user = findViewById(R.id.pusat_bantuan_user);

        panah_bantuan_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        pusat_bantuan_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bantuan_user.this,pusatbantuan.class));
            }
        });
        WA_user.setOnClickListener(new View.OnClickListener() {
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
