package com.mykostaja.kostaja.Pemilik;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
