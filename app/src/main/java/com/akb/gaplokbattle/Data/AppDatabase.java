package com.akb.gaplokbattle.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.akb.gaplokbattle.Model.History;

@Database(entities = {History.class},version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //Untuk mengakses Database menggunakan method abstract
    public abstract HistoryDao historyDao();
}