package com.mykostaja.kostaja;

import static android.text.TextUtils.isEmpty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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
        holder.tv_alamat_kost.setText(datakost.getAlamat());
        Glide.with(holder.itemView.getContext()).load(datakost.getGambar()).into(holder.iv_card);
    }


    @Override
    public int getItemCount() {
        return listdatakost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Menginisialisasi variabel pada layout view detail kost

        private TextView tv_nama_kost,tv_alamat_kost;
        private ImageView iv_card;
        RelativeLayout layout_cardlist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama_kost = itemView.findViewById(R.id.tv_nama_kost);
            tv_alamat_kost = itemView.findViewById(R.id.tv_alamat_kost);
            iv_card = itemView.findViewById(R.id.iv_card);
            layout_cardlist = itemView.findViewById(R.id.layout_cardlist);



        }
    }
}
