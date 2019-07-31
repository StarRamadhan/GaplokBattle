package com.akb.gaplokbattle.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akb.gaplokbattle.R;

public class MainActivity extends AppCompatActivity {
    TextView btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void StartGame(View view) {
        Intent intent = new Intent(MainActivity.this, JankenActivity.class);
        startActivity(intent);
    }
    public void HistoryGame(View view) {
        Intent intent = new Intent(MainActivity.this, ReadDataActivity.class);
        startActivity(intent);
    }
    public void PhotoGame(View view) {
        Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
        startActivity(intent);
    }
    public void ExitGame(View view) {
        finish();
        System.exit(0);
    }
}
