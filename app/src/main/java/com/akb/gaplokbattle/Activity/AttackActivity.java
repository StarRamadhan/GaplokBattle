package com.akb.gaplokbattle.Activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.akb.gaplokbattle.Data.AppDatabase;
import com.akb.gaplokbattle.Model.History;
import com.akb.gaplokbattle.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AttackActivity extends AppCompatActivity implements SensorEventListener {
    private TextInputEditText waktu, status, power;
    private AppDatabase database;

    private float lastY, lastZ;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaY = 0;
    private float deltaZ = 0;
    private float allMax = 0;

    private float vibrateThreshold = 0;

    private TextView txtAllMax;
    private TextView Y;
    private TextView Z;


    public Vibrator vibs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_attack);
        txtAllMax = (TextView) findViewById(R.id.accel_value);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;

        } else {
            // fai! we dont have an accelerometer!
        }

        //inisialisasi vibration
        vibs = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbGaplokBattle")
                .build();
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


    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(AttackActivity.this,MainActivity.class);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'At' HH:mm:ss z");
        String curDT = sdf.format(new Date());


        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        //Batas minimal sensor
        if (deltaY < 15)
            deltaY = 0;
        if (deltaZ < 15)
            deltaZ = 0;

        if (deltaY > deltaYMax) {
            deltaYMax = deltaY;
            //Y.setText(Float.toString(deltaYMax));
        }
        if (deltaZ > deltaZMax) {
            deltaZMax = deltaZ;
            //Z.setText(Float.toString(deltaZMax));
        }
        allMax = Math.round(deltaYMax * deltaZMax);
        if (allMax < 500) {
            allMax = 0;
            txtAllMax.setText("0");
        } else if(allMax>=500){
            txtAllMax.setText(Float.toString(allMax));
            final History data =new History();
            //data.getId();
            data.setPower(Float.toString(allMax));
            data.setStatus("Win");
            data.setWaktu(curDT);


            //MediaPlayer mp = MediaPlayer.create(this,R.raw.slaps);
            //mp.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    insertData(data);
                    vibs.vibrate(50);
                    Intent intents = new Intent(AttackActivity.this, JankenActivity.class);
                        intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intents);
                        finish();
                    }
            }, 750);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
