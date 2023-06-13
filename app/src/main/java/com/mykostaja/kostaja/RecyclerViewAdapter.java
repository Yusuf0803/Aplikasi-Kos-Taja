package com.mykostaja.kostaja;

import static android.text.TextUtils.isEmpty;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    ArrayList <data_kost> listdatakost;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public  RecyclerViewAdapter(ArrayList<data_kost> listdatakost){
        this.listdatakost = listdatakost;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        final String nama_kos = listdatakost.get(position).getNama_kost();
        final String tipe_kos = listdatakost.get(position).getTipe_kost();
        final String provinsi = listdatakost.get(position).getProvinsi();
        final String kabupaten = listdatakost.get(position).getKabupaten();
        final String kecamatan = listdatakost.get(position).getKecamatan();
        final String status = listdatakost.get(position).getStatus();
        final String luas = listdatakost.get(position).getLuas();
        final String alamat = listdatakost.get(position).getAlamat();
        final String fasilitas = listdatakost.get(position).getFasilitas();
        final String gambar = listdatakost.get(position).getGambar();

        //Memasukkan nilai kedalam view

        holder.nama_kost_detail.setText(nama_kos);
        holder.tipe_kost_detail.setText(tipe_kos);
        holder.provinsi_detail.setText(provinsi);
        holder.kabupaten_detail.setText(kabupaten);
        holder.kecamatan_detail.setText(kecamatan);
        holder.status_detail.setText(status);
        holder.luas_detail.setText(luas);
        holder.alamat_detail.setText(alamat);
        holder.fasilitas_detail.setText(fasilitas);

        if (isEmpty(gambar)){
            holder.gambar_detail.setImageResource(R.drawable.orang);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load(gambar.trim())
                    .into(holder.gambar_detail);
        }

    }



    @Override
    public int getItemCount() {
        return listdatakost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Menginisialisasi variabel pada layout view detail kost

        private TextView nama_kost_detail, tipe_kost_detail, provinsi_detail, kabupaten_detail, kecamatan_detail, status_detail, luas_detail, alamat_detail, fasilitas_detail;
        private ImageView gambar_detail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_kost_detail = itemView.findViewById(R.id.nama_kost_detail);
            tipe_kost_detail = itemView.findViewById(R.id.tipe_kost_detail);
            provinsi_detail = itemView.findViewById(R.id.provinsi_detail);
            kabupaten_detail = itemView.findViewById(R.id.kabupaten_detail);
            kecamatan_detail = itemView.findViewById(R.id.kecamatan_detail);
            status_detail = itemView.findViewById(R.id.status_detail);
            luas_detail = itemView.findViewById(R.id.luas_detail);
            alamat_detail = itemView.findViewById(R.id.alamat_detail);
            fasilitas_detail = itemView.findViewById(R.id.fasilitas_detail);
            gambar_detail = itemView.findViewById(R.id.gambar_detail);


        }
    }
}
