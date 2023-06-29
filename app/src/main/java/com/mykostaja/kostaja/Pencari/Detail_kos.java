package com.mykostaja.kostaja.Pencari;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.mykostaja.kostaja.R;
public class Detail_kos extends AppCompatActivity {
    private Button btn_ajukan_sewa;

    private TextView nama_kost_detail, tipe_kost_detail, provinsi_detail,
            kabupaten_detail, kecamatan_detail, status_detail, luas_detail, alamat_detail, fasilitas_detail, harga_detail, nohp_detail;

    private ImageView gambar_detail;

    String getNama_kost, getKey1,getTipe_kost, getProvinsi, getKabupaten, getKecamatan, getStatus, getLuas, getAlamat, getFasilitas, getHargakost, getNohp, getGambar;

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
        gambar_detail = findViewById(R.id.gambar_detail);
        fasilitas_detail = findViewById(R.id.fasilitas_detail);
        harga_detail = findViewById(R.id.harga_detail);
        nohp_detail = findViewById(R.id.nohp_detail);
        btn_ajukan_sewa = findViewById(R.id.btn_ajukan_sewa);

        btn_ajukan_sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getNohp = getIntent().getStringExtra("nohp");
                String url = "https://wa.me/62"+getNohp.substring(1); // Ganti dengan URL yang ingin Anda tuju
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

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
        getHargakost = getIntent().getStringExtra("harga");
        getNohp = getIntent().getStringExtra("nohp");
        getFasilitas = getIntent().getStringExtra("fasilitas");
        getGambar = getIntent().getStringExtra("gambar");
        getKey1 = getIntent().getStringExtra("key");

        Glide.with(Detail_kos.this).load(getGambar.trim()).into(gambar_detail);
        nama_kost_detail.setText(getNama_kost);
        tipe_kost_detail.setText(getTipe_kost);
        provinsi_detail.setText(getProvinsi);
        kabupaten_detail.setText(getKabupaten);
        kecamatan_detail.setText(getKecamatan);
        status_detail.setText(getStatus);
        luas_detail.setText(getLuas);
        alamat_detail.setText(getAlamat);
        harga_detail.setText(getHargakost + " /bln");
        nohp_detail.setText(getNohp);
        fasilitas_detail.setText(getFasilitas);

    }
}