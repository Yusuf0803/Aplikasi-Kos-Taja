package com.mykostaja.kostaja.DataKost;

public class data_kost1 {
    private String namakost, tipekost, provinsi, kabupaten, kecamatan,alamatkost, statuskost, luaskost, hargakost, fasilitaskost, nohp,gambar;
    private String key;

    public data_kost1() {
    }

    public data_kost1(String namakost, String tipekost, String provinsi, String kabupaten,
                      String kecamatan, String alamatkost, String statuskost, String luaskost,
                      String hargakost, String fasilitaskost, String nohp, String gambar) {
        this.namakost = namakost;
        this.tipekost = tipekost;
        this.provinsi = provinsi;
        this.kabupaten = kabupaten;
        this.kecamatan = kecamatan;
        this.alamatkost = alamatkost;
        this.statuskost = statuskost;
        this.luaskost = luaskost;
        this.hargakost = hargakost;
        this.fasilitaskost = fasilitaskost;
        this.nohp = nohp;
        this.gambar = gambar;
    }

    public String getNamakost() {
        return namakost;
    }

    public void setNamakost(String namakost) {
        this.namakost = namakost;
    }

    public String getTipekost() {
        return tipekost;
    }

    public void setTipekost(String tipekost) {
        this.tipekost = tipekost;
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

    public String getAlamatkost() {
        return alamatkost;
    }

    public void setAlamatkost(String alamatkost) {
        this.alamatkost = alamatkost;
    }

    public String getStatuskost() {
        return statuskost;
    }

    public void setStatuskost(String statuskost) {
        this.statuskost = statuskost;
    }

    public String getLuaskost() {
        return luaskost;
    }

    public void setLuaskost(String luaskost) {
        this.luaskost = luaskost;
    }

    public String getHargakost() {
        return hargakost;
    }

    public void setHargakost(String hargakost) {
        this.hargakost = hargakost;
    }

    public String getFasilitaskost() {
        return fasilitaskost;
    }

    public void setFasilitaskost(String fasilitaskost) {
        this.fasilitaskost = fasilitaskost;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
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