package com.tiara.datasiswa;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

//TODO 1 : kelas ini digunakan untuk membuat entity room database
@Entity
public class DataModel implements Parcelable {

    // TODO 2 : ini tu ibarat  klo di database apa aja yg mau di simpen di database buat tablenya
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo (name = "name")
    String name;

    @ColumnInfo(name = "harga")
    String harga;

    @ColumnInfo(name = "path_picture")
    String pathPicture;


    public DataModel(){

    }


    protected DataModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        harga = in.readString();
        pathPicture = in.readString();
        //TODO 3 : setelah selesai ini di klik kanan -> pilih generate -> pilih getter and setter


    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    //TODO 4  : Ini fungsi untuk nge get dan nge set data dari database lokal
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(harga);
        parcel.writeString(pathPicture);
    }

}
