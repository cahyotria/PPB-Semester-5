package com.example.crudfirebase;

public class Barang {
    String key;

    String KodeBarang, NamaBarang, Satuan, HargaBeli, HargaJual, Stok, StokMin;

    public Barang() {
    }

    public Barang(String kodeBarang, String namaBarang, String satuan, String hargaBeli, String hargaJual, String stok, String stokMin) {
        KodeBarang = kodeBarang;
        NamaBarang = namaBarang;
        Satuan = satuan;
        HargaBeli = hargaBeli;
        HargaJual = hargaJual;
        Stok = stok;
        StokMin = stokMin;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKodeBarang() {
        return KodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        KodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        NamaBarang = namaBarang;
    }

    public String getSatuan() {
        return Satuan;
    }

    public void setSatuan(String satuan) {
        Satuan = satuan;
    }

    public String getHargaBeli() {
        return HargaBeli;
    }

    public void setHargaBeli(String hargaBeli) {
        HargaBeli = hargaBeli;
    }

    public String getHargaJual() {
        return HargaJual;
    }

    public void setHargaJual(String hargaJual) {
        HargaJual = hargaJual;
    }

    public String getStok() {
        return Stok;
    }

    public void setStok(String stok) {
        Stok = stok;
    }

    public String getStokMin() {
        return StokMin;
    }

    public void setStokMin(String stokMin) {
        StokMin = stokMin;
    }
}
