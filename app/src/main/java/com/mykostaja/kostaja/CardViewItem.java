package com.mykostaja.kostaja;

public class CardViewItem {

    //inisialisasi pada layout card list
    private String iv_card;
    private String tv_nama_kost;
    private String tv_alamat_kost;
    private String key;

    public CardViewItem(String iv_card, String tv_nama_kost,String tv_alamat_kost){
        this.iv_card = iv_card;
        this.tv_nama_kost = tv_nama_kost;
        this.tv_alamat_kost = tv_alamat_kost;
    }

    public String getIv_card() {
        return iv_card;
    }

    public void setIv_card(String iv_card) {
        this.iv_card = iv_card;
    }

    public String getTv_nama_kost() {
        return tv_nama_kost;
    }

    public void setTv_nama_kost(String tv_nama_kost) {
        this.tv_nama_kost = tv_nama_kost;
    }

    public String getTv_alamat_kost() {
        return tv_alamat_kost;
    }

    public void setTv_alamat_kost(String tv_alamat_kost) {
        this.tv_alamat_kost = tv_alamat_kost;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
