package com.mykostaja.kostaja;

public class data_kost {

    private String nama_kost;
    private String tipe_kost;
    private String nohp_hub;
    private String key;

    public data_kost(String nama_kost, String tipe_kost, String nohp_hub){

        this.nama_kost = nama_kost;
        this.tipe_kost = tipe_kost;
        this.nohp_hub = nohp_hub;

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

    public String getNohp_hub() {
        return nohp_hub;
    }

    public void setNohp_hub(String nohp_hub) {
        this.nohp_hub = nohp_hub;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
