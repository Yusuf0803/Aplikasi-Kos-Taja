package com.mykostaja.kostaja.Pemilik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mykostaja.kostaja.R;

import java.util.ArrayList;

public class view_data_kost extends AppCompatActivity {

    private ArrayList<com.mykostaja.kostaja.DataKost.data_kost1> data_kost1;

    private Button btn_update_view;

    private TextView nama_kost_view,tipe_kost_view,provinsi_view
            ,kabupaten_view,kecamatan_view,status_view,luas_view,alamat_view,fasilitas_view,harga_view,nohp_view;

    private ImageView gambar_view;

    String getNama_kost1,getKey1, getTipe_kost1, getProvinsi1, getKabupaten1, getKecamatan1, getStatus1, getLuas1, getAlamat1, getFasilitas1,getHargakost1,getNohp1,getGambar1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_kost);


        nama_kost_view = findViewById(R.id.nama_kost_view);
        tipe_kost_view = findViewById(R.id.tipe_kost_view);
        provinsi_view = findViewById(R.id.provinsi_view);
        kabupaten_view = findViewById(R.id.kabupaten_view);
        kecamatan_view = findViewById(R.id.kecamatan_view);
        status_view = findViewById(R.id.status_view);
        luas_view = findViewById(R.id.luas_view);
        alamat_view = findViewById(R.id.alamat_view);
        fasilitas_view = findViewById(R.id.fasilitas_view);
        harga_view = findViewById(R.id.harga_view);
        nohp_view = findViewById(R.id.nohp_view);
        gambar_view = findViewById(R.id.gambar_view);
        btn_update_view = findViewById(R.id.btn_update_view);

        GetSetKost();

    }

    private void GetSetKost() {

        getNama_kost1 = getIntent().getStringExtra("nama_kost");
        getTipe_kost1 = getIntent().getStringExtra("tipe_kost");
        getProvinsi1 = getIntent().getStringExtra("provinsi");
        getKabupaten1 = getIntent().getStringExtra("kabupaten");
        getKecamatan1 = getIntent().getStringExtra("kecamatan");
        getStatus1 = getIntent().getStringExtra("status");
        getLuas1 = getIntent().getStringExtra("luas");
        getAlamat1 = getIntent().getStringExtra("alamat");
        getHargakost1 = getIntent().getStringExtra("harga");
        getNohp1 = getIntent().getStringExtra("nohp");
        getFasilitas1 = getIntent().getStringExtra("fasilitas");
        getGambar1 = getIntent().getStringExtra("gambar");
        getKey1 = getIntent().getStringExtra("key");

        //dtt
        Glide.with(view_data_kost.this).load(getGambar1.trim()).into(gambar_view);
        nama_kost_view.setText(getNama_kost1);
        tipe_kost_view.setText(getTipe_kost1);
        provinsi_view.setText(getProvinsi1);
        kabupaten_view.setText(getKabupaten1);
        kecamatan_view.setText(getKecamatan1);
        status_view.setText(getStatus1);
        luas_view.setText(getLuas1);
        alamat_view.setText(getAlamat1);
        harga_view.setText(getHargakost1 + " /bln");
        nohp_view.setText(getNohp1);
        fasilitas_view.setText(getFasilitas1);

        btn_update_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view_data_kost.this,update_datakost.class);

                i.putExtra("nama_kost", getNama_kost1);
                i.putExtra("tipe_kost", getTipe_kost1);
                i.putExtra("provinsi", getProvinsi1);
                i.putExtra("kabupaten", getKabupaten1);
                i.putExtra("kecamatan", getKecamatan1);
                i.putExtra("status", getStatus1);
                i.putExtra("luas", getLuas1);
                i.putExtra("alamat", getAlamat1);
                i.putExtra("harga", getHargakost1);
                i.putExtra("nohp", getNohp1);
                i.putExtra("fasilitas", getFasilitas1);
                i.putExtra("gambar", getGambar1);
                i.putExtra("key", getKey1);

                startActivity(i);
            }
        });
    }
}
