package com.akb.gaplokbattle.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.akb.gaplokbattle.Model.History;

@Dao
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertHistory(History history);

    @Query("SELECT * FROM tHistory")
    History[] readDataHistory();

//    @Query("DELETE FROM tHistory")
//    void deleteAll();
}
