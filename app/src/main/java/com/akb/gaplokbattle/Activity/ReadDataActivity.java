package com.akb.gaplokbattle.Activity;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.akb.gaplokbattle.Data.AppDatabase;
import com.akb.gaplokbattle.Model.History;
import com.akb.gaplokbattle.R;
import com.akb.gaplokbattle.adapter.RecyclerHistoryAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadDataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<History> daftarHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //getSupportActionBar().setTitle("Daftar History");

        //Inisialisasi ArrayList
        daftarHistory = new ArrayList<>();

        //Inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbGaplokBattle").allowMainThreadQueries().build();

        /*
         * Mengambil data Mahasiswa dari Database
         * lalu memasukannya ke kedalam ArrayList (daftarMahasiswa)
         */
        daftarHistory.addAll(Arrays.asList(database.historyDao().readDataHistory()));

        recyclerView = findViewById(R.id.dataItem);
        //Agar ukuran RecyclerView tidak berubah
        recyclerView.setHasFixedSize(true);

        //Menentukan bagaimana item pada RecyclerView akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Mamasang adapter pada RecyclerView
        adapter = new RecyclerHistoryAdapter(daftarHistory, ReadDataActivity.this);
        recyclerView.setAdapter(adapter);
    }
}
