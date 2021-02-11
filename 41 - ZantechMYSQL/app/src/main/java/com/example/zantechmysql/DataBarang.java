package com.example.zantechmysql;

public class DataBarang {
    String kode_barang, nama_barang, satuan;
    Integer harga_jual, harga_beli, stok, stok_min;

    public DataBarang() {
    }

    public DataBarang(String kode_barang, String nama_barang, String satuan, Integer harga_jual, Integer harga_beli, Integer stok, Integer stok_min) {
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.satuan = satuan;
        this.harga_jual = harga_jual;
        this.harga_beli = harga_beli;
        this.stok = stok;
        this.stok_min = stok_min;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Integer getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(Integer harga_jual) {
        this.harga_jual = harga_jual;
    }

    public Integer getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(Integer harga_beli) {
        this.harga_beli = harga_beli;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getStok_min() {
        return stok_min;
    }

    public void setStok_min(Integer stok_min) {
        this.stok_min = stok_min;
    }
}
