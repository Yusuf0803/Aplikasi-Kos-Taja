package com.mykostaja.kostaja.Pencari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mykostaja.kostaja.CardViewAdapter;
import com.mykostaja.kostaja.CardViewItem;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_User extends AppCompatActivity {

    TextView tv_nama_kost,tv_alamat_kost;
    ImageView iv_card;
    BottomNavigationView nav_bottom;
    View recyclerViewAdapter;
    CardViewAdapter cardViewAdapter;
    List<RecyclerViewAdapter> viewItemList;

    //Deklarasi varibel database
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;

    private SearchView cari_main_user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user);

        //tv card list
        tv_nama_kost = findViewById(R.id.tv_nama_kost);
        tv_alamat_kost = findViewById(R.id.tv_alamat_kost);

        //iv card list
        iv_card = findViewById(R.id.iv_card);



        //mendapatkan referensi database
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        String kost = getIntent().getStringExtra("kost");
        //reclerview
        recyclerViewAdapter = findViewById(R.id.recyclerview_main_user);

        viewItemList = new ArrayList<>();
//        cardViewAdapter = new CardViewAdapter(this,viewItemList);


        //mendapatkan data dari firebase
//        getData("");

        //memfungsikan search
        cari_main_user = findViewById(R.id.cari_main_user);
//        cari_main_user.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (s.toString().isEmpty()){
//                    getData(s.toString());
//                }else {
//                    adapter.getFilter().filter(s);
//                }
//            }
//        });

        nav_bottom = findViewById(R.id.nav_bottom);
        nav_bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.cari_kos_user:
                        Intent cari_kos_user = new Intent(MainActivity_User.this,MainActivity_User.class);
                        Toast.makeText(MainActivity_User.this, "Cari Kos", Toast.LENGTH_SHORT);
                        return true;
                    case R.id.Kos_Saya_user:
                        Toast.makeText(MainActivity_User.this, "Kos Saya", Toast.LENGTH_SHORT);
                        return true;

                    case R.id.Pesan_user:
                        Intent intent1 = new Intent(MainActivity_User.this,chat_user.class);
                        Toast.makeText(MainActivity_User.this, "Chat", Toast.LENGTH_SHORT).show();
                        MainActivity_User.this.startActivity(intent1);
                        return true;

                    case R.id.Profil_user:
                        Intent intent = new Intent(MainActivity_User.this, profil_user.class);
                        Toast.makeText(MainActivity_User.this, "Profil", Toast.LENGTH_SHORT).show();
                        MainActivity_User.this.startActivity(intent);
                        return true;
                }
                return MainActivity_User.super.onOptionsItemSelected(item);
            }
        });

    }

//    private void getData(String data) {
    //menampilkan data dari item yang dipilih sebelumya
//
//        final  String iv_card = getIntent().getExtras().getString("getGambar");
//        final  String tv_nama_kost = getIntent().getExtras().getString("getNama");
//        final  String tv_alamat_kost = getIntent().getExtras().getString("getAlamat");


//        final String nama_kos = getIntent().getExtras().getString()
//        final String tipe_kos = listdatakost.get(position).getTipe_kost();
//        final String provinsi = listdatakost.get(position).getProvinsi();
//        final String kabupaten = listdatakost.get(position).getKabupaten();
//        final String kecamatan = listdatakost.get(position).getKecamatan();
//        final String status = listdatakost.get(position).getStatus();
//        final String luas = listdatakost.get(position).getLuas();
//        final String alamat = listdatakost.get(position).getAlamat();
//        final String fasilitas = listdatakost.get(position).getFasilitas();
//        final String gambar = listdatakost.get(position).getGambar();


        //mengatur tampilan gambar

//    }

}