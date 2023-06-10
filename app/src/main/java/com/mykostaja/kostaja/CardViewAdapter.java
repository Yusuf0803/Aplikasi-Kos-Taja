package com.mykostaja.kostaja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder>{

    private List<CardViewItem> CardItemList;
    private Context context;

    public CardViewAdapter(List<CardViewItem> CardItemList, Context context){
        this.CardItemList = CardItemList;
        this.context = context;
    }
    @NonNull
    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_datakos, parent, false);
    return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewAdapter.ViewHolder holder, int position) {
    CardViewItem cardViewItem = CardItemList.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
