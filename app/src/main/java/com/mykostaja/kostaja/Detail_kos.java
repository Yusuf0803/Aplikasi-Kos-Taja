package com.mykostaja.kostaja;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Detail_kos extends AppCompatActivity {

    private ArrayList<data_kost> data_kosts;

    private TextView nama_kost_detail,tipe_kost_detail,provinsi_detail
            ,kabupaten_detail,kecamatan_detail,status_detail,luas_detail,alamat_detail,fasilitas_detail;

    private ImageView gambar_detail;

    String getNama_kost, getTipe_kost, getProvinsi, getKabupaten, getKecamatan, getStatus, getLuas, getAlamat, getFasilitas,getGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_kos);

        nama_kost_detail = findViewById(R.id.nama_kost_detail);
        tipe_kost_detail = findViewById(R.id.tipe_kost_detail);
        provinsi_detail = findViewById(R.id.provinsi_detail);
        kabupaten_detail = findViewById(R.id.kabupaten_detail);
        kecamatan_detail = findViewById(R.id.kecamatan_detail);
        status_detail = findViewById(R.id.status_detail);
        luas_detail = findViewById(R.id.luas_detail);
        alamat_detail = findViewById(R.id.alamat_detail);
        fasilitas_detail = findViewById(R.id.fasilitas_detail);

        GetSetKost();

    }

    private void GetSetKost() {

        getNama_kost = getIntent().getStringExtra("nama_kost");
        getTipe_kost = getIntent().getStringExtra("tipe_kost");
        getProvinsi = getIntent().getStringExtra("provinsi");
        getKabupaten = getIntent().getStringExtra("kabupaten");
        getKecamatan = getIntent().getStringExtra("kecamatan");
        getStatus = getIntent().getStringExtra("status");
        getLuas = getIntent().getStringExtra("luas");
        getAlamat = getIntent().getStringExtra("alamat");
        getFasilitas = getIntent().getStringExtra("fasilitas");
        getGambar = getIntent().getStringExtra("gambar");

//        Glide.with(Detail_kos.this).load(getGambar.trim()).into(gambar_detail);
        nama_kost_detail.setText(getNama_kost);
        tipe_kost_detail.setText(getTipe_kost);
        provinsi_detail.setText(getProvinsi);
        kabupaten_detail.setText(getKabupaten);
        kecamatan_detail.setText(getKecamatan);
        status_detail.setText(getStatus);
        luas_detail.setText(getLuas);
        alamat_detail.setText(getAlamat);
        fasilitas_detail.setText(getFasilitas);

    }
}
