package com.mykostaja.kostaja.AddDataKost;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mykostaja.kostaja.Pemilik.MainActivity_Admin;
import com.mykostaja.kostaja.R;
import com.mykostaja.kostaja.DataKost.data_kost1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Add_datakos1 extends AppCompatActivity {
    private EditText nama_kost1, Alamat1, Status_kost1, Luas_kost1,Fasilitas_kost1,Nohp, Harga_kost1;

    private Button create1, image1;
    private ImageView image_create1;
    private Spinner tipe_kost1, provinsi1, kabupaten1, kecamatan1;

    private String getNamakost, getTipekost, getProvinsi, getKabupaten, getKecamatan, getAlamatkost,
        getStatuskost, getLuaskost,getHargakost,getFasilitaskost,getNohp, getGambar;

    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;

    private DatabaseReference dbF;
    private StorageReference dbS;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_datakos1);

        nama_kost1 = findViewById(R.id.Cnama_kost1);
        tipe_kost1 = findViewById(R.id.Ctipe_kost1);
        provinsi1 = findViewById(R.id.Cprovinsi_kost1);
        kabupaten1 = findViewById(R.id.Ckabupaten_kost1);
        kecamatan1 = findViewById(R.id.Ckecamatan_kost1);
        Alamat1 = findViewById(R.id.Calamatlengkapkost1);
        create1 = findViewById(R.id.Csimpan1);
        image1 = findViewById(R.id.Cphoto1);
        image_create1 = findViewById(R.id.image_create1);
        Fasilitas_kost1 = findViewById(R.id.Cfasilitaskost1);
        Harga_kost1 = findViewById(R.id.Chargakost1);
        Nohp = findViewById(R.id.Cnohp1);

        Status_kost1 = findViewById(R.id.Cstatus_kost1);
        Luas_kost1 = findViewById(R.id.Cluas_kamar1);

        // Mendapatkan referensi dari firebase storage
        dbS = FirebaseStorage.getInstance().getReference();

        // Mendapatkan referensi dari database firebase
        dbF = FirebaseDatabase.getInstance().getReference();


        create1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getNamakost = nama_kost1.getText().toString();
                getTipekost = tipe_kost1.getSelectedItem().toString();
                getProvinsi = provinsi1.getSelectedItem().toString();
                getKabupaten = kabupaten1.getSelectedItem().toString();
                getKecamatan = kecamatan1.getSelectedItem().toString();
                getAlamatkost = Alamat1.getText().toString();
                getFasilitaskost = Fasilitas_kost1.getText().toString();
                getStatuskost = Status_kost1.getText().toString();
                getLuaskost = Luas_kost1.getText().toString();
                getHargakost = Harga_kost1.getText().toString();
                getNohp = Nohp.getText().toString();
                getGambar = image_create1.toString().trim();
                addKos();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
    }
    private void getImage() {
        Intent imageIntentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(imageIntentGallery, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    image_create1.setVisibility(View.VISIBLE);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    image_create1.setImageBitmap(bitmap);
                }
                break;
            case REQUEST_CODE_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        image_create1.setBackground(null);
                        image_create1.setVisibility(View.VISIBLE);
                        image_create1.setImageDrawable(drawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }




    private void addKos() {

        boolean a, b, c, d, e, f, g,h,i,j,k,l;

        a = TextUtils.isEmpty(getNamakost);
        b = TextUtils.isEmpty(getAlamatkost);
        c = TextUtils.isEmpty(getStatuskost);
        d = TextUtils.isEmpty(getLuaskost);
        e = TextUtils.isEmpty(getKecamatan);
        f = TextUtils.isEmpty(getFasilitaskost);
        g = TextUtils.isEmpty(getGambar);
        h = TextUtils.isEmpty(getNohp);
        i = TextUtils.isEmpty(getHargakost);
        j = TextUtils.isEmpty(getProvinsi);
        k = TextUtils.isEmpty(getKabupaten);
        l = TextUtils.isEmpty(getHargakost);

        if (a || b || c || d || g || e || f || g || h || i || j || k || l) {
            Toast.makeText(Add_datakos1.this, "Masih terdapat data yang kosong!", Toast.LENGTH_SHORT).show();
        } else {

            image_create1.setDrawingCacheEnabled(true);
            image_create1.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) image_create1.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            // Mengkompres Bitmap menjadi JPG dengan kualitas gambar 100%
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();

            // Lokasi lengkap dimana gambar akan disimpan
            String namaFile = UUID.randomUUID() + ".jpg";
            final String pathImage = "gambar/" + namaFile;
            UploadTask uploadTask = dbS.child(pathImage).putBytes(bytes);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString().trim();
                            String key = dbF.child("Kos").push().getKey();
                            data_kost1 kos = new data_kost1(getNamakost, getTipekost, getProvinsi,
                                    getKabupaten, getKecamatan, getAlamatkost, getStatuskost, getLuaskost,
                                    getHargakost, getFasilitaskost, getNohp, imageUrl);
                            kos.setKey(key);

                            dbF.child("Kos").child(key).setValue(kos).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Hal ini terjadi saat user berhasil menyimpan datanya kedalam database
                                        nama_kost1.setText("");
                                        Alamat1.setText("");
                                        Fasilitas_kost1.setText("");
                                        Status_kost1.setText("");
                                        Luas_kost1.setText("");
                                        Harga_kost1.setText("");
                                        Nohp.setText("");

                                        Toast.makeText(Add_datakos1.this, "Kos berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Add_datakos1.this, MainActivity_Admin.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Add_datakos1.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_datakos1.this, "Upload Gagal", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                }
            });

        }
    }
}
