package com.example.datasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataSiswaOpenHelper extends SQLiteOpenHelper {

    private static final String NAMA_DATABASE   = "siswa.db";
    private static final String NAMA_TABLE      = "datasiswa";

    private static final String COL_1           = "ID";
    private static final String COL_2           = "NIM";
    private static final String COL_3           = "NAMA";

    public DataSiswaOpenHelper(@Nullable Context context) {
        super(context, NAMA_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NAMA_TABLE + " ("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_2 + " TEXT, "
                + COL_3 + " TEXT "
                + ")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NAMA_TABLE);
    }

    public boolean insertData(String nim, String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, nim);
        values.put(COL_3, nama);
        long result = db.insert(NAMA_TABLE, null, values);
        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + NAMA_TABLE, null);
    }

    public boolean updateData(String id, String nim, String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_1, id);
        values.put(COL_2, nim);
        values.put(COL_3, nama);

        db.update(NAMA_TABLE, values, COL_1 + " = ? ", new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NAMA_TABLE, COL_1 + " = ? ", new String[]{id});
    }
}
