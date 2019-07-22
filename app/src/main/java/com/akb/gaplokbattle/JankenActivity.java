package com.akb.gaplokbattle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class JankenActivity extends AppCompatActivity {
    //Button btn_rock, btn_paper, btn_scissor;
    ImageView iv_enemy, iv_player;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janken);
//        btn_scissor = (Button) findViewById(R.id.btn_scissor);
//        btn_rock = (Button) findViewById(R.id.btn_rock);
//        btn_paper = (Button) findViewById(R.id.btn_paper);

        iv_player = (ImageView) findViewById(R.id.iv_player);
        iv_enemy = (ImageView) findViewById(R.id.iv_enemy);

        findViewById(R.id.btn_scissor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_player.setImageResource(R.drawable.ic_scissor);
                String message = lets_play("scissor");
                Toast.makeText(JankenActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_rock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_player.setImageResource(R.drawable.ic_rock);
                String message = lets_play("rock");
                Toast.makeText(JankenActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_paper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_player.setImageResource(R.drawable.ic_paper);
                String message = lets_play("paper");
                Toast.makeText(JankenActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String lets_play(String player_janken){
        String enemy_janken = "";
        Random r = new Random();

        //janken select 1,2, or 3
        int enemy_janken_number = r.nextInt(3)+1;

        if (enemy_janken_number == 1 ){
            enemy_janken = "scissor";
        }else if (enemy_janken_number == 2) {
            enemy_janken = "rock";
        }else if (enemy_janken_number == 3) {
            enemy_janken = "paper";
        }

        //set enemy image for janken
        if (enemy_janken.equals("scissor")){
            iv_enemy.setImageResource(R.drawable.ic_scissor);
        }else if (enemy_janken.equals("rock")){
            iv_enemy.setImageResource(R.drawable.ic_rock);
        }else if (enemy_janken.equals("paper")){
            iv_enemy.setImageResource(R.drawable.ic_paper);
        }

        //select winner of janken
        if (enemy_janken.equals(player_janken)){
            return "Draw";
        }else if ((enemy_janken.equals("rock") && player_janken.equals("scissor")) ||
                  (enemy_janken.equals("scissor") && player_janken.equals("paper")) ||
                  (enemy_janken.equals("paper") && player_janken.equals("rock"))) {
            return "Watch Out, GAPLOKAN Will Come !!!";
        }else if ((enemy_janken.equals("scissor") && player_janken.equals("rock")) ||
                (enemy_janken.equals("rock") && player_janken.equals("paper")) ||
                (enemy_janken.equals("paper") && player_janken.equals("scissor"))){
            return "GAPLOK Him !!!!!!";
        }
        return enemy_janken;
    }
}
