package com.example.datasiswa;

public class dataSiswa {

    private String id;
    private String nim;
    private String nama;

    private dataSiswa(){}

    public dataSiswa(String id,String nim, String nama) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
