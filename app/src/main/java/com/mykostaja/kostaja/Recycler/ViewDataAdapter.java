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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mykostaja.kostaja.DataKost.data_kost1;
import com.mykostaja.kostaja.Pemilik.MainActivity_Admin;
import com.mykostaja.kostaja.Pemilik.view_data_kost;
import com.mykostaja.kostaja.R;

import java.util.ArrayList;

public class ViewDataAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<data_kost1> listdatakost;
    Context context;

    public ViewDataAdapter(ArrayList<data_kost1> listdatakost, Context context) {
        this.listdatakost = listdatakost;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_list_kost, parent, false);
        return new RecyclerViewAdapter.ViewHolder(v);
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
        Glide.with(holder.itemView.getContext()).load(datakost1.getGambar()).into(holder.iv_card);

        holder.layout_cardlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, view_data_kost.class);
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
                intent.putExtra("key", datakost1.getKey());
                context.startActivity(intent);
            }
        });

        holder.hapus_kos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin untuk menghapus data ?")
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String key = listdatakost.get(position).getKey();
                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Kos").child(key);
                                db.removeValue()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Jika penghapusan berhasil, hapus juga item dari daftar RecyclerView
                                                listdatakost.remove(position);
                                                notifyItemRemoved(position);
                                                notifyItemRangeChanged(position, listdatakost.size());
                                                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Jika penghapusan gagal, tampilkan pesan kesalahan
                                                Toast.makeText(context, "Gagal menghapus data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }
        });    }

    @Override
    public int getItemCount() {
        return listdatakost.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Menginisialisasi variabel pada layout view detail kost
        TextView tv_nama_kost;
        TextView tv_alamat_kost;
        TextView tv_status_kost;
        TextView tv_luas_kost;
        TextView tv_nohp;
        ImageView iv_card;
        RelativeLayout layout_cardlist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_card = itemView.findViewById(R.id.iv_card);
            tv_nama_kost = itemView.findViewById(R.id.tv_nama_kost);
            tv_status_kost = itemView.findViewById(R.id.tv_status_kost);
            tv_luas_kost = itemView.findViewById(R.id.tv_luas_kost);
            tv_alamat_kost = itemView.findViewById(R.id.tv_alamat_kost);
            tv_nohp = itemView.findViewById(R.id.tv_nohp);
            layout_cardlist = itemView.findViewById(R.id.layout_cardlist);


        }
    }
}
