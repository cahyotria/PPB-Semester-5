package com.example.ta_zantech;

public class History {
    String id_history, nama_produk, harga, tanggal, jam;

    public History() {
    }

    public History(String id_history, String nama_produk, String harga, String tanggal, String jam) {
        this.id_history = id_history;
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.tanggal = tanggal;
        this.jam = jam;
    }

    public String getId_history() {
        return id_history;
    }

    public void setId_history(String id_history) {
        this.id_history = id_history;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
