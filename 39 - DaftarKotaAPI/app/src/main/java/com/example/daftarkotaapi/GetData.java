package com.example.daftarkotaapi;

import com.google.gson.annotations.SerializedName;

public class GetData {

    private int id;

    @SerializedName("body")
    private String kota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}
