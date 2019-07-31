package com.akb.gaplokbattle.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.akb.gaplokbattle.R;

public class SplashActivity extends AppCompatActivity {
    private int waktu_loading=1500;

    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent menu=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(menu);
                finish();

            }
        },waktu_loading);
    }
}