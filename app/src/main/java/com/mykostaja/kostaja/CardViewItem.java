package com.mykostaja.kostaja;

public class CardViewItem {

    private int image;
    private String Deskripsi;
    private String key;

    public CardViewItem(int image, String Deskripsi){
        this.image = image;
        this.Deskripsi = Deskripsi;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
