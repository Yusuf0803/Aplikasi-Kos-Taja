//package com.mykostaja.kostaja;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import java.io.ByteArrayOutputStream;
//import java.util.UUID;
//
//public class Add_datakos extends AppCompatActivity {
//private EditText nama_kost, provinsi, kabupaten, kecamatan, alamat, deskripsi;
//private Button create,image;
//private ImageView image_create;
//private Spinner tipe_kost;
//
//String getNama_kost,getTipe_kost,getProvinsi,getKabupaten,getKecamatan,getAlamat,getDeskripsi,getGambar;
//
//public Uri url;public Bitmap bitmap;
//
//private static final int REQUEST_CODE_CAMERA = 1;
//private static final int REQUEST_CODE_GALLERY = 2;
//
//DatabaseReference dbF;StorageReference dbS;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_datakos);
//
//        nama_kost=findViewById(R.id.Cnama_kos);
//        tipe_kost=findViewById(R.id.Ctipe_kos);
//        provinsi=findViewById(R.id.Cprovinsi_kos);
//        kabupaten=findViewById(R.id.Ckabupaten_kos);
//        kecamatan=findViewById(R.id.Ckecamatan_kos);
//        alamat=findViewById(R.id.Calamatlengkapkos);
//        deskripsi=findViewById(R.id.Cdeskkos);
//        create=findViewById(R.id.Csimpan);
//        image=findViewById(R.id.Cphoto);
//
//        //Mendapatkan referensi dari firebase storage
//        dbS = FirebaseStorage.getInstance().getReference();
//
//        //Mendapatkan referensi dari database firebase
//        dbF = FirebaseDatabase.getInstance().getReference();
//
//
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getimage();
//            }
//        });
//
//
//        create.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean a,b,c,d,e,f,g;
//
//                getNama_kost = nama_kost.getText().toString();
//                getTipe_kost = tipe_kost.getSelectedItem().toString();
//                getProvinsi = provinsi.getText().toString();
//                getKabupaten = kabupaten.getText().toString();
//                getKecamatan = kecamatan.getText().toString();
//                getAlamat = alamat.getText().toString();
//                getDeskripsi = deskripsi.getText().toString();
//                getGambar = image_create.toString().trim();
//
//
//                a= TextUtils.isEmpty(getNama_kost);
//                b= TextUtils.isEmpty(getAlamat);
//                c= TextUtils.isEmpty(getProvinsi);
//                d= TextUtils.isEmpty(getKabupaten);
//                f= TextUtils.isEmpty(getKecamatan);
//                g= TextUtils.isEmpty(getDeskripsi);
//
//                if(a||b||c||d|f||g){
//                    Toast.makeText(Add_datakos.this,"Masih tedapat data yang kosong !",Toast.LENGTH_SHORT).show();
//                }else{
//                    addkos();
//                }
//            }
//        });
//
//    }
//
//    private void addkos(){
//        image_create.setDrawingCacheEnabled(true);
//        image_create.buildDrawingCache();
//        Bitmap bitmap = ((BitmapDrawable) image_create.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//        //Mengkompres Bitmap menjadi JPG dengan kualitas gambar 100%
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] bytes = stream.toByteArray();
//
//        //lokasi lengkap dimana gambar akan disimpan
//        String namaFile = UUID.randomUUID() + ".jpg";
//        final String pathImage = "gambar/" + namaFile;
//        UploadTask uploadTask = dbS.child(pathImage).putBytes(bytes);uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        dbF.child("Admin").child("Kos").push()
//                                .setValue(new data_kost(getNama_kost, getTipe_kost, getProvinsi, getKabupaten, getKecamatan, getAlamat, getDeskripsi, uri.toString().trim()))
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        //Hal ini terjadi saat user berhasil menyimpan datanya kedalam database
//                                        nama_kost.setText("");
//                                        provinsi.setText("");
//                                        kabupaten.setText("");
//                                        kecamatan.setText("");
//                                        alamat.setText("");
//                                        deskripsi.setText("");
//
//                                        Toast.makeText(Add_datakos.this,"Kos berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(Add_datakos.this, MainActivity_Admin.class));
//                                        finish();
//                                    }
//                                });
//                    }
//                });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Add_datakos.this, "Upload Gagal", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
////                progress_crud.setVisibility(View.VISIBLE);
////                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
////                progress_crud.setProgress((int) progress);
//            }
//        });
//    }
//
//    private void getimage() {
//        Intent imageIntentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(imageIntentGallery, 2);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //menghandle hasil data yang diambil dari kamera atau galeri pada ImageView
//        switch (requestCode) {
//            case REQUEST_CODE_CAMERA:
//                if (resultCode == RESULT_OK) {
//                    image_create.setVisibility(View.VISIBLE);
//                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                    image_create.setImageBitmap(bitmap);
//                }
//                break;
//
//            case REQUEST_CODE_GALLERY:
//                if (resultCode == RESULT_OK) {
//                    image_create.setVisibility(View.VISIBLE);
//                    url = data.getData();
//                    image_create.setImageURI(url);
//                }
//                break;
//        }
//    }
//
//}













package com.mykostaja.kostaja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class Add_datakos extends AppCompatActivity {
    private EditText nama_kost, provinsi, kabupaten, kecamatan, alamat, deskripsi;
    private Button create, image;
    private ImageView image_create;
    private Spinner tipe_kost;

    private String getNama_kost, getTipe_kost, getProvinsi, getKabupaten, getKecamatan, getAlamat, getDeskripsi, getGambar;

    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;

    private DatabaseReference dbF;
    private StorageReference dbS;

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

        // Mendapatkan referensi dari firebase storage
        dbS = FirebaseStorage.getInstance().getReference();

        // Mendapatkan referensi dari database firebase
        dbF = FirebaseDatabase.getInstance().getReference();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a, b, c, d, e, f, g;

                getNama_kost = nama_kost.getText().toString();
                getTipe_kost = tipe_kost.getSelectedItem().toString();
                getProvinsi = provinsi.getText().toString();
                getKabupaten = kabupaten.getText().toString();
                getKecamatan = kecamatan.getText().toString();
                getAlamat = alamat.getText().toString();
                getDeskripsi = deskripsi.getText().toString();

                a = TextUtils.isEmpty(getNama_kost);
                b = TextUtils.isEmpty(getAlamat);
                c = TextUtils.isEmpty(getProvinsi);
                d = TextUtils.isEmpty(getKabupaten);
                f = TextUtils.isEmpty(getKecamatan);
                g = TextUtils.isEmpty(getDeskripsi);

                if (a || b || c || d || f || g) {
                    Toast.makeText(Add_datakos.this, "Masih terdapat data yang kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    addKos();
                }
            }
        });
    }

    private void addKos() {
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
                        String key = dbF.child("Admin").child("Kos").push().getKey();
                        data_kost kos = new data_kost(getNama_kost, getTipe_kost, getProvinsi, getKabupaten, getKecamatan, getAlamat, getDeskripsi, imageUrl);
                        kos.setKey(key);

                        dbF.child("Admin").child("Kos").child(key)
                                .setValue(kos)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Hal ini terjadi saat user berhasil menyimpan datanya kedalam database
                                            nama_kost.setText("");
                                            provinsi.setText("");
                                            kabupaten.setText("");
                                            kecamatan.setText("");
                                            alamat.setText("");
                                            deskripsi.setText("");
                                            image_create.setImageDrawable(null);

                                            Toast.makeText(Add_datakos.this, "Kos berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Add_datakos.this, MainActivity_Admin.class));
                                            finish();
                                        } else {
                                            Toast.makeText(Add_datakos.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                                        }
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

    private void getImage() {
        Intent imageIntentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(imageIntentGallery, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Menghandle hasil data yang diambil dari kamera atau galeri pada ImageView
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CAMERA:
                    image_create.setVisibility(View.VISIBLE);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    image_create.setImageBitmap(bitmap);
                    break;
                case REQUEST_CODE_GALLERY:
                    image_create.setVisibility(View.VISIBLE);
                    Uri imageUri = data.getData();
                    image_create.setImageURI(imageUri);
                    break;
            }
        }
    }
}
