package com.mykostaja.kostaja;

public class data_kost {

    private String nama_kost, tipe_kost, provinsi, kabupaten, kecamatan, alamat, fasilitas, status, luas,gambar;
    private String key;

    public data_kost(String nama_kost, String tipe_kost, String provinsi, String kabupaten, String kecamatan, String alamat, String fasilitas, String status, String luas, String imageUrl) {
        this.nama_kost = nama_kost;
        this.tipe_kost = tipe_kost;
        this.provinsi = provinsi;
        this.kabupaten = kabupaten;
        this.kecamatan = kecamatan;
        this.status = status;
        this.luas = luas;
        this.alamat = alamat;
        this.fasilitas = fasilitas;
        this.gambar = imageUrl;
    }

    public String getNama_kost() {
        return nama_kost;
    }

    public void setNama_kost(String nama_kost) {
        this.nama_kost = nama_kost;
    }

    public String getTipe_kost() {
        return tipe_kost;
    }

    public void setTipe_kost(String tipe_kost) {
        this.tipe_kost = tipe_kost;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
