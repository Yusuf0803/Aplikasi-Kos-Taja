package com.mykostaja.kostaja;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    ArrayList <data_kost> listdatakost;
    Context context;

    public RecyclerViewAdapter(ArrayList<data_kost> listdatakost, Context context) {
        this.listdatakost = listdatakost;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_list_kost, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        //Memasukkan nilai kedalam view
        data_kost datakost = listdatakost.get(position);

        holder.tv_nama_kost.setText(datakost.getNama_kost());
        holder.tv_status_kost.setText(datakost.getStatus());
        holder.tv_luas_kost.setText(datakost.getLuas());
        holder.tv_alamat_kost.setText(datakost.getAlamat());
//        String foto = datakost.getGambar();
//        Glide.with(holder.itemView.getContext()).load(foto).into(holder.iv_card);
        Glide.with(holder.itemView.getContext()).load(datakost.getGambar()).into(holder.iv_card);

        holder.layout_cardlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Detail_kos.class);
                intent.putExtra("gambar",datakost.getGambar());
                intent.putExtra("nama_kost",datakost.getNama_kost());
                intent.putExtra("tipe_kost",datakost.getTipe_kost());
                intent.putExtra("provinsi",datakost.getProvinsi());
                intent.putExtra("kabupaten",datakost.getKabupaten());
                intent.putExtra("kecamatan",datakost.getKecamatan());
                intent.putExtra("status",datakost.getStatus());
                intent.putExtra("luas",datakost.getLuas());
                intent.putExtra("alamat",datakost.getAlamat());
                intent.putExtra("fasilitas",datakost.getFasilitas());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdatakost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Menginisialisasi variabel pada layout view detail kost

        private TextView tv_nama_kost,tv_alamat_kost,tv_status_kost, tv_luas_kost;
        private ImageView iv_card;
        RelativeLayout layout_cardlist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_card = itemView.findViewById(R.id.iv_card);
            tv_nama_kost = itemView.findViewById(R.id.tv_nama_kost);
            tv_status_kost = itemView.findViewById(R.id.tv_status_kost);
            tv_luas_kost = itemView.findViewById(R.id.tv_luas_kost);
            tv_alamat_kost = itemView.findViewById(R.id.tv_alamat_kost);
            layout_cardlist = itemView.findViewById(R.id.layout_cardlist);

        }
    }
}
