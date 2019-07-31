package com.akb.gaplokbattle.Activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

import com.akb.gaplokbattle.Data.AppDatabase;
import com.akb.gaplokbattle.Model.History;
import com.akb.gaplokbattle.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AttackedActivity extends AppCompatActivity {
    public Vibrator vibs;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_attacked);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'At' HH:mm:ss z");
        String curDT = sdf.format(new Date());

        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbGaplokBattle")
                .build();

        vibs = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(500);

        History data =new History();
        //data.getId();
        data.setPower("~");
        data.setStatus("Lose");
        data.setWaktu(curDT);
        insertData(data);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intents = new Intent(getApplicationContext(), JankenActivity.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intents);
                //finish();
            }
        },1500);
    }
    //run INSERT method
    @SuppressLint("StaticFieldLeak")
    private void insertData(final History history){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.historyDao().insertHistory(history);
            }

//            @Override
//            protected void onPostExecute(Long status) {
//                //Menandakan bahwa data berhasil disimpan
//                Toast.makeText(AttackActivity.this, "Status Row "+status, Toast.LENGTH_SHORT).show();
//            }
        }.execute();
    }
}
