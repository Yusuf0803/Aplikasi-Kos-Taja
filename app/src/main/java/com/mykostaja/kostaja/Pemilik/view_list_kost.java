package com.mykostaja.kostaja.Pemilik;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mykostaja.kostaja.DataKost.data_kost1;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.Recycler.RecyclerViewAdapter;
import com.mykostaja.kostaja.Recycler.ViewDataAdapter;

import java.util.ArrayList;

public class view_list_kost extends AppCompatActivity {


    ViewDataAdapter recyclerViewAdapter;
    ArrayList<data_kost1> datakost1;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list_kost);
        recyclerView = findViewById(R.id.recyclerview_list_kost);
        databaseReference = FirebaseDatabase.getInstance().getReference("Kos");
        recyclerView.setLayoutManager(new LinearLayoutManager(view_list_kost.this));
        datakost1 = new ArrayList<>();
        recyclerViewAdapter = new ViewDataAdapter(datakost1, view_list_kost.this);
        recyclerView.setAdapter(recyclerViewAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    data_kost1 data_kos = dataSnapshot.getValue(data_kost1.class);
                    datakost1.add(data_kos);
                    data_kos.setKey(dataSnapshot.getKey());
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
