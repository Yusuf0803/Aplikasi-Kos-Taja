package com.mykostaja.kostaja;

import static android.text.TextUtils.isEmpty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder>{

    List<CardViewItem> listdatakost;
    Context context;

    public CardViewAdapter (Context context, List<CardViewItem> listdatakost){
        this.context = context;
        this.listdatakost = listdatakost;
    }

    @NonNull
    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    context = parent.getContext();
//    LayoutInflater inflater = LayoutInflater.from(context);
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_kost, parent, false);
    return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewAdapter.ViewHolder holder, int position) {
        CardViewItem cardViewItem = listdatakost.get(position);


        final String iv_card = listdatakost.get(position).getIv_card();
        final String tv_nama_kost = listdatakost.get(position).getTv_nama_kost();
        final String tv_alamat_kost = listdatakost.get(position).getTv_alamat_kost();

        holder.tv_nama_kost.setText(tv_nama_kost);
        holder.tv_alamat_kost.setText(tv_alamat_kost);
//        Glide.with(context).load(cardViewItem.getIv_card()).into(holder.iv_card);
//        holder.tv_nama_kost.setText(cardViewItem.getTv_nama_kost());
//        holder.tv_alamat_kost.setText(cardViewItem.getTv_alamat_kost());

        if (isEmpty(iv_card)){
            holder.iv_card.setImageResource(R.drawable.question_mark);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load(iv_card.trim())
                    .into(holder.iv_card);
        }


        if (isEmpty((CharSequence) cardViewItem)){
            holder.iv_card.setImageResource(R.drawable.question_mark);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load(cardViewItem.getIv_card())
                    .into(holder.iv_card);
        }
    }


    @Override
    public int getItemCount() {
        return listdatakost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       private TextView tv_nama_kost,tv_alamat_kost;
        private ImageView iv_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama_kost = itemView.findViewById(R.id.tv_nama_kost);
            tv_alamat_kost = itemView.findViewById(R.id.tv_alamat_kost);
            iv_card = itemView.findViewById(R.id.iv_card);

        }
    }
}
