package com.mykostaja.kostaja;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Detail_kos extends AppCompatActivity {

    private ArrayList<data_kost> data_kosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_kos);


        String nama_kost = getIntent().getStringExtra("nama_kost");
        String tipe_kost = getIntent().getStringExtra("tipe_kost");
        String provinsi = getIntent().getStringExtra("provinsi");
        String kabupaten = getIntent().getStringExtra("kabupaten");
        String kecamatan = getIntent().getStringExtra("kecamatan");
        String alamat = getIntent().getStringExtra("alamat");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        String gambar = getIntent().getStringExtra("gambar");





    }
}
