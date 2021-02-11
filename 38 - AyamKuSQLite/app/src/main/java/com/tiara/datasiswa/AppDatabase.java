package com.tiara.datasiswa;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

//TODO 1: ini berfungsi untuk menmpilkan entity2 nya dan version Database

@Database(entities = {DataModel.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase{
    public abstract DataDAO userDao();

}
