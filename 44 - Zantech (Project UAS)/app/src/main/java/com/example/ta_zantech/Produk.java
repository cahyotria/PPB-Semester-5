package com.example.ta_zantech;

public class Produk {

    String id_produk,
            kode_produk,
            nama_produk,
            harga, stok,
            deskripsi,
            image;

    public Produk() {
    }

    public Produk(String id_produk, String kode_produk, String nama_produk, String harga, String stok, String deskripsi, String image) {
        this.id_produk = id_produk;
        this.kode_produk = kode_produk;
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.stok = stok;
        this.deskripsi = deskripsi;
        this.image = image;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
