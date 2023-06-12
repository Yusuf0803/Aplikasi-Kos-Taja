package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class Add_datakos extends AppCompatActivity {
    private EditText nama_kost, alamat, deskripsi, status_kost, luas_kamar;
    private Button create, image;
    private ImageView image_create;
    private Spinner tipe_kost, provinsi, kabupaten, kecamatan;

    private String getNama_kost, getTipe_kost, getProvinsi, getKabupaten, getKecamatan, getAlamat, getDeskripsi, getstatus_kost, getluas_kamar, getGambar;

    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;

    private DatabaseReference dbF;
    private StorageReference dbS;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_datakos);

        nama_kost = findViewById(R.id.Cnama_kos);
        tipe_kost = findViewById(R.id.Ctipe_kos);
        provinsi = findViewById(R.id.Cprovinsi_kos);
        kabupaten = findViewById(R.id.Ckabupaten_kos);
        kecamatan = findViewById(R.id.Ckecamatan_kos);
        alamat = findViewById(R.id.Calamatlengkapkos);
        deskripsi = findViewById(R.id.Cdeskkos);
        create = findViewById(R.id.Csimpan);
        image = findViewById(R.id.Cphoto);
        image_create = findViewById(R.id.image_create);

        status_kost = findViewById(R.id.Cstatus_kost);
        luas_kamar = findViewById(R.id.Cluas_kamar);

        // Mendapatkan referensi dari firebase storage
        dbS = FirebaseStorage.getInstance().getReference();

        // Mendapatkan referensi dari database firebase
        dbF = FirebaseDatabase.getInstance().getReference();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean a, b, c, d, e, f, g;

                getNama_kost = nama_kost.getText().toString();
                getTipe_kost = tipe_kost.getSelectedItem().toString();
                getProvinsi = provinsi.getSelectedItem().toString();
                getKabupaten = kabupaten.getSelectedItem().toString();
                getKecamatan = kecamatan.getSelectedItem().toString();
                getAlamat = alamat.getText().toString();
                getDeskripsi = deskripsi.getText().toString();
                getstatus_kost = status_kost.getText().toString();
                getluas_kamar = luas_kamar.getText().toString();
                getGambar = image_create.toString().trim();

//                a = TextUtils.isEmpty(getNama_kost);
//                b = TextUtils.isEmpty(getAlamat);
//                c = TextUtils.isEmpty(getProvinsi);
//                d = TextUtils.isEmpty(getKabupaten);
//                f = TextUtils.isEmpty(getKecamatan);
//                g = TextUtils.isEmpty(getDeskripsi);
//                e = TextUtils.isEmpty(getGambar);
//
//                if (a || b || c || d || f || g || e) {
//                    Toast.makeText(Add_datakos.this, "Masih terdapat data yang kosong!", Toast.LENGTH_SHORT).show();
//                } else {
//
//                }
                addKos();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
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
        // Menghandle hasil data yang diambil dari kamera atau galeri pada ImageView
//        if (resultCode == RESULT_OK) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    image_create.setVisibility(View.VISIBLE);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    image_create.setImageBitmap(bitmap);
                }
                break;
            case REQUEST_CODE_GALLERY:
                if (resultCode == RESULT_OK) {
                    image_create.setVisibility(View.VISIBLE);
                    Uri imageUri = data.getData();
                    image_create.setImageURI(imageUri);
                }
                break;
        }
//        }
    }
    private void addKos() {

        boolean a, b, c, d, e, f, g;

        a = TextUtils.isEmpty(getNama_kost);
        b = TextUtils.isEmpty(getAlamat);
        c = TextUtils.isEmpty(getstatus_kost);
        d = TextUtils.isEmpty(getluas_kamar);
//        f = TextUtils.isEmpty(getKecamatan);
        g = TextUtils.isEmpty(getDeskripsi);
        e = TextUtils.isEmpty(getGambar);

        if (a || b || c || d || g || e) {
            Toast.makeText(Add_datakos.this, "Masih terdapat data yang kosong!", Toast.LENGTH_SHORT).show();
        } else {

            image_create.setDrawingCacheEnabled(true);
            image_create.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) image_create.getDrawable()).getBitmap();
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
                            data_kost kos = new data_kost(getNama_kost, getTipe_kost, getProvinsi, getKabupaten, getKecamatan, getAlamat, getDeskripsi, getstatus_kost,getluas_kamar,imageUrl);
                            kos.setKey(key);

                            dbF.child("Kos").child(key)
                                    .setValue(kos)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
                                            // Hal ini terjadi saat user berhasil menyimpan datanya kedalam database
                                            nama_kost.setText("");
//                                            provinsi.setText("");
//                                            kabupaten.setText("");
//                                            kecamatan.setText("");
                                            alamat.setText("");
                                            deskripsi.setText("");
                                            status_kost.setText("");
                                            luas_kamar.setText("");

//                                            image_create.setImageDrawable(null);
//                                                image_create.setImageBitmap(null);

                                            Toast.makeText(Add_datakos.this, "Kos berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Add_datakos.this, MainActivity_Admin.class));
                                            finish();
//                                            }
//                                            else {
//                                                Toast.makeText(Add_datakos.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
//                                            }
                                        }
                                    });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_datakos.this, "Upload Gagal", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    // Mengatur tampilan progress jika diperlukan
                }
            });
        }
    }
}
