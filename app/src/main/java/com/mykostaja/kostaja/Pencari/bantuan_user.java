package com.mykostaja.kostaja.Pencari;

import android.annotation.SuppressLint;
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
    }
}
