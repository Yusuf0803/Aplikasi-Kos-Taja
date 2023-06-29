package com.mykostaja.kostaja.Recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mykostaja.kostaja.Login;
import com.mykostaja.kostaja.Pemilik.profil_admin;
import com.mykostaja.kostaja.Pencari.Detail_kos;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.DataKost.data_kost1;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<data_kost1> listdatakost;
    Context context;

    public RecyclerViewAdapter(ArrayList<data_kost1> listdatakost, Context context) {
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
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Memasukkan nilai kedalam view
        data_kost1 datakost1 = listdatakost.get(position);

        holder.tv_nama_kost.setText(datakost1.getNamakost());
        holder.tv_status_kost.setText(datakost1.getStatuskost());
        holder.tv_luas_kost.setText(datakost1.getLuaskost());
        holder.tv_alamat_kost.setText(datakost1.getAlamatkost());
        holder.tv_nohp.setText(datakost1.getNohp());
        holder.hapus_kos.setVisibility(View.GONE);
//        String foto = datakost.getGambar();
//        Glide.with(holder.itemView.getContext()).load(foto).into(holder.iv_card);
        Glide.with(holder.itemView.getContext()).load(datakost1.getGambar()).into(holder.iv_card);

        holder.layout_cardlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Detail_kos.class);
                intent.putExtra("gambar", datakost1.getGambar());
                intent.putExtra("nama_kost", datakost1.getNamakost());
                intent.putExtra("tipe_kost", datakost1.getTipekost());
                intent.putExtra("provinsi", datakost1.getProvinsi());
                intent.putExtra("kabupaten", datakost1.getKabupaten());
                intent.putExtra("kecamatan", datakost1.getKecamatan());
                intent.putExtra("status", datakost1.getStatuskost());
                intent.putExtra("luas", datakost1.getLuaskost());
                intent.putExtra("alamat", datakost1.getAlamatkost());
                intent.putExtra("harga", datakost1.getHargakost());
                intent.putExtra("nohp", datakost1.getNohp());
                intent.putExtra("fasilitas", datakost1.getFasilitaskost());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listdatakost.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Menginisialisasi variabel pada layout view detail kost
        TextView tv_nama_kost;
        TextView tv_alamat_kost;
        TextView tv_status_kost;
        AppCompatButton hapus_kos;
        TextView tv_luas_kost;
        TextView tv_nohp;
        ImageView iv_card;
        RelativeLayout layout_cardlist;
        ArrayList<data_kost1> listdatakost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.listdatakost = listdatakost;

            iv_card = itemView.findViewById(R.id.iv_card);
            tv_nama_kost = itemView.findViewById(R.id.tv_nama_kost);
            tv_status_kost = itemView.findViewById(R.id.tv_status_kost);
            tv_luas_kost = itemView.findViewById(R.id.tv_luas_kost);
            tv_alamat_kost = itemView.findViewById(R.id.tv_alamat_kost);
            tv_nohp = itemView.findViewById(R.id.tv_nohp);
            layout_cardlist = itemView.findViewById(R.id.layout_cardlist);

            hapus_kos = itemView.findViewById(R.id.bt_hapus_kos);

        }
    }
}
