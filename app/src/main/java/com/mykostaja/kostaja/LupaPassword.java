package com.mykostaja.kostaja;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LupaPassword extends AppCompatActivity {

    private EditText Email_Verifikasi;
    private TextView panah_lupapswd;
    private Button Btn_Verifikasi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lupapassword);

        panah_lupapswd = findViewById(R.id.panah_lupapswd);
        Email_Verifikasi = findViewById(R.id.Email_Verifikasi);
        Btn_Verifikasi = findViewById(R.id.Btn_Verifikasi);

        panah_lupapswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
