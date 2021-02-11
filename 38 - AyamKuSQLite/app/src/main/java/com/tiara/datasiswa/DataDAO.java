package com.tiara.datasiswa;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DataDAO {

    @Query("SELECT * FROM DataModel ")
    List<DataModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(DataModel dataModel);

    @Delete
    public void deleteUsers(DataModel dataModel);

    @Update
    public void update(DataModel dataModel);

}
