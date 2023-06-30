package com.mykostaja.kostaja.Pencari;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mykostaja.kostaja.DataUser.DataUser;
import com.mykostaja.kostaja.R;

public class edit_profil_user extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView panah_edit_profil;
    private EditText nama_edit_user, email_edit_user, nohp_edit_user;
    private Button simpan_edit_user, btn_UpFto_eduser;
    private ImageView gambar_edit_profil;
    DatabaseReference databaseReference;
    String getusername, getkey, getemail, getnohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil_user);

        panah_edit_profil = findViewById(R.id.tv_panah_eduser);
        gambar_edit_profil = findViewById(R.id.iv_gambar_eduser);
        nama_edit_user = findViewById(R.id.ed_nama_eduser);
        email_edit_user = findViewById(R.id.ed_email_eduser);
        nohp_edit_user = findViewById(R.id.ed_nohp_eduser);
        simpan_edit_user = findViewById(R.id.btn_simpan_eduser);
        btn_UpFto_eduser = findViewById(R.id.btn_UpFto_eduser);

        // Mendapatkan referensi database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        panah_edit_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Mengatur onClickListener untuk tombol Upload
        btn_UpFto_eduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuka galeri gambar untuk memilih gambar
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        // Mengatur onClickListener untuk tombol Simpan
        simpan_edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan data yang ingin disimpan
                getusername = nama_edit_user.getText().toString().trim();
                getemail = email_edit_user.getText().toString().trim();
                getnohp = nohp_edit_user.getText().toString().trim();
                getkey = getIntent().getStringExtra("key");

                // Melakukan penyimpanan data ke Firebase Realtime Database
                String userId = "Pencari"; // ID pengguna Pencari
                DataUser dataUser = new DataUser("", getusername, getnohp, getemail, "", "");

                // Simpan dataUser ke Firebase Realtime Database
                databaseReference.child("User").child(userId).setValue(dataUser);

                // Setelah data tersimpan, Anda dapat kembali ke activity sebelumnya
                Intent intent = new Intent(edit_profil_user.this,edit_profil_user.class);
                intent.putExtra("data_terbaru", String.valueOf(dataUser));
                startActivity(intent);
                finish();
            }
        });

        // Mendapatkan data yang ingin diedit dari Intent
        getusername = getIntent().getStringExtra("nama");
        getemail = getIntent().getStringExtra("email");
        getnohp = getIntent().getStringExtra("nohp");
        getkey = getIntent().getStringExtra("key");

        // Menampilkan data pada EditText
        nama_edit_user.setText(getusername);
        email_edit_user.setText(getemail);
        nohp_edit_user.setText(getnohp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Mendapatkan URI gambar yang dipilih dari galeri
            Uri uri = data.getData();

            // Menampilkan gambar yang dipilih pada ImageView
            gambar_edit_profil.setImageURI(uri);
        }
    }
}