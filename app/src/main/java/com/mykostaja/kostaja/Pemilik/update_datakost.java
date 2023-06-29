package com.mykostaja.kostaja.Pemilik;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mykostaja.kostaja.DataKost.data_kost1;
import com.mykostaja.kostaja.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

public class update_datakost extends AppCompatActivity {

    private String[] ListTipe,ListProvinsi,ListKabupaten,ListKecamatan;
    private EditText updatenama, updatestatus, updateluas, updatealamat, updateharga, updatefasilitas, updatenohp;
    private Spinner update_tipe_kost, update_provinsi_kost, update_kabupaten_kost, update_kecamatan_kost;
    private DatabaseReference dbF;
    private StorageReference dbS;
    private ImageView imageupdate; //fotolama
    Uri imageUri;
    private Button btn_updatefoto, btn_upadatedata;
    private String cekNama,cekKey, cekTipe, cekProvinsi, cekKabupaten, cekKecamatan, cekStatus, cekLuas, cekHarga, cekNohp, cekAlamat, cekFasilitas, cekGambar;

    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_datakos1);



        //EditText
        updatenama = findViewById(R.id.update_nama_kost);
        updatestatus = findViewById(R.id.update_status_kost);
        updateluas = findViewById(R.id.update_luas_kamar);
        updatenohp = findViewById(R.id.updatenohp);
        updatefasilitas = findViewById(R.id.updatefasilitaskost);
        updateharga = findViewById(R.id.updatehargakost);
        updatealamat = findViewById(R.id.updatealamat);

        //spinner
        update_tipe_kost = findViewById(R.id.update_tipe_kost);
        update_provinsi_kost = findViewById(R.id.update_provinsi_kost);
        update_kabupaten_kost = findViewById(R.id.update_kabupaten_kost);
        update_kecamatan_kost = findViewById(R.id.update_kecamatan_kost);

        //gambar
        imageupdate = findViewById(R.id.image_update);
        //btn
        btn_upadatedata = findViewById(R.id.btn_updatedata);
        btn_updatefoto = findViewById(R.id.btn_updatefoto);

        btn_updatefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });
        //
        dbF = FirebaseDatabase.getInstance().getReference();
        dbS = FirebaseStorage.getInstance().getReference();

        getData();
        setData();

        btn_upadatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekNama = updatenama.getText().toString();
                cekTipe = update_tipe_kost.getSelectedItem().toString();
                cekProvinsi = update_provinsi_kost.getSelectedItem().toString();
                cekKabupaten = update_kabupaten_kost.getSelectedItem().toString();
                cekKecamatan = update_kecamatan_kost.getSelectedItem().toString();
                cekStatus = updatestatus.getText().toString();
                cekLuas = updateluas.getText().toString();
                cekAlamat = updatealamat.getText().toString();
                cekHarga = updateharga.getText().toString();
                cekNohp = updatenohp.getText().toString();
                cekFasilitas = updatefasilitas.getText().toString();

                if (TextUtils.isEmpty(cekNama)) {
                    updatenama.setError("Tidak Boleh Kosong");
                    updatenama.requestFocus();
                } else if (TextUtils.isEmpty(cekStatus)) {
                    updatestatus.setError("Tidak Boleh Kosong");
                    updatestatus.requestFocus();
                } else if (TextUtils.isEmpty(cekAlamat)) {
                    updatealamat.setError("Tidak Boleh Kosong");
                    updatealamat.requestFocus();
                } else if (TextUtils.isEmpty(cekHarga)) {
                    updateharga.setError("Tidak Boleh Kosong");
                    updateharga.requestFocus();
                } else if (TextUtils.isEmpty(cekNohp)) {
                    updatenohp.setError("Tidak Boleh Kosong");
                    updatenohp.requestFocus();
                } else if (TextUtils.isEmpty(cekFasilitas)) {
                    updatefasilitas.setError("Tidak Boleh Kosong");
                    updatefasilitas.requestFocus();
                }

                updatedatakost();
            }
            private void updatedatakost() {

                cekGambar = getIntent().getStringExtra("gambar");
                cekKey = getIntent().getStringExtra("key");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference gambarstorage = storage.getReferenceFromUrl(cekGambar.trim());
                StorageReference reference = FirebaseStorage.getInstance().getReference();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference = database.getReference("Kos");



                        imageupdate.setDrawingCacheEnabled(true);
                        imageupdate.buildDrawingCache();
                        Bitmap bitmap = ((BitmapDrawable) imageupdate.getDrawable()).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] bytes = stream.toByteArray();
                        String namaFile = UUID.randomUUID() + ".jpg";
                        final String pathImage = "gambar/" + namaFile;
                        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        getReference.child(cekKey).setValue(new data_kost1(cekNama, cekTipe, cekProvinsi, cekKabupaten, cekKecamatan,
                                                cekAlamat, cekStatus, cekLuas, cekHarga, cekFasilitas, cekNohp, uri.toString().trim())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                new AlertDialog.Builder(update_datakost.this)
                                                        .setMessage("Data berhasil di update")
                                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                updatenama.setText("");
                                                                updatealamat.setText("");
                                                                updatefasilitas.setText("");
                                                                updatestatus.setText("");
                                                                updateluas.setText("");
                                                                updateharga.setText("");
                                                                updatenohp.setText("");
                                                                startActivity(new Intent(update_datakost.this, MainActivity_Admin.class));
                                                                finish();
                                                            }
                                                        }).show();


                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(update_datakost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(update_datakost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });

                            }
                        });

            }
        });
    }

    private void getImage() {
        Intent imageIntenGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(imageIntenGallery, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    imageupdate.setVisibility(View.VISIBLE);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imageupdate.setImageBitmap(bitmap);
                }
                break;
            case REQUEST_CODE_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        imageupdate.setBackground(null);
                        imageupdate.setVisibility(View.VISIBLE);
                        imageupdate.setImageDrawable(drawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }
    }

    public static String generateRandomId(){
        final String Karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int ID_LENGTH = 10;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i<ID_LENGTH; i++){
            int randomIndex = random.nextInt(Karakter.length());
            char randomChar = Karakter.charAt(randomIndex);
            sb.append(randomChar);
        }
        return  sb.toString();
    }
    private void setData(){
        updatenama.setText(cekNama);
        updatealamat.setText(cekAlamat);
        updatestatus.setText(cekStatus);
        updateluas.setText(cekLuas);
        updateharga.setText(cekHarga);
        updatefasilitas.setText(cekFasilitas);
        updatenohp.setText(cekNohp);
        Glide.with(update_datakost.this)
                .load(cekGambar.trim())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageupdate.setBackground(null); // Hapus latar belakang yang ada
                        imageupdate.setImageDrawable(resource);
                        imageupdate.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Do nothing
                    }
                });

//        update_provinsi_kost.setSelection(Integer.parseInt(cekProvinsi));
//        update_kabupaten_kost.setSelection(Integer.parseInt(cekKabupaten));
//        update_kecamatan_kost.setSelection(Integer.parseInt(cekKecamatan));
    }
    private void getData() {
        cekNama = getIntent().getStringExtra("nama_kost");
        cekTipe = getIntent().getStringExtra("tipe_kost");
        cekProvinsi = getIntent().getStringExtra("provinsi");
        cekKabupaten = getIntent().getStringExtra("kabupaten");
        cekKecamatan = getIntent().getStringExtra("kecamatan");
        cekStatus = getIntent().getStringExtra("status");
        cekLuas = getIntent().getStringExtra("luas");
        cekAlamat = getIntent().getStringExtra("alamat");
        cekHarga = getIntent().getStringExtra("harga");
        cekNohp = getIntent().getStringExtra("nohp");
        cekFasilitas = getIntent().getStringExtra("fasilitas");
        cekGambar = getIntent().getStringExtra("gambar");

        ListTipe = new String[]{"Pria", "Wanita", "Campuran"};
        ArrayAdapter<String> TipeAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ListTipe);
        update_tipe_kost.setAdapter(TipeAdapter);
        update_tipe_kost.setSelection(TipeAdapter.getPosition(cekTipe.trim()));

        ListProvinsi = new String[]{"Jawa Tengah", "Jawa Timur", "Jawa Barat"};
        ArrayAdapter<String> ProvinsiAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ListProvinsi);
        update_provinsi_kost.setAdapter(ProvinsiAdapter);
        update_provinsi_kost.setSelection(ProvinsiAdapter.getPosition(cekProvinsi.trim()));

        ListKabupaten = new String[]{"Banyumas", "Cilacap", "Banjarnegara","Purbalingga"};
        ArrayAdapter<String> KabupatenAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ListKabupaten);
        update_kabupaten_kost.setAdapter(KabupatenAdapter);
        update_kabupaten_kost.setSelection(KabupatenAdapter.getPosition(cekKabupaten.trim()));

        ListKecamatan = new String[]{"Purwokerto Timur", "Purwokerto Barat", "Purwokerto Selatan", "Purwokerto Utara","Lumbir","Sokaraja","Pekalongan"};
        ArrayAdapter<String> KecamatanAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ListKecamatan);
        update_kecamatan_kost.setAdapter(KecamatanAdapter);
        update_kecamatan_kost.setSelection(KecamatanAdapter.getPosition(cekKecamatan.trim()));

    }
}
