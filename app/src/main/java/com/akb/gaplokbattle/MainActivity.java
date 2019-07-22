package com.akb.gaplokbattle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        //Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
    }
}
