package com.akb.gaplokbattle.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.akb.gaplokbattle.R;

import java.util.Random;


public class JankenActivity extends AppCompatActivity {

    public Vibrator vibs;

    //Button btn_rock, btn_paper, btn_scissor;
    private static final String TAG = "JankenActivity";

    ImageView iv_enemy, iv_player;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janken);

        iv_player = (ImageView) findViewById(R.id.iv_player);
        iv_enemy = (ImageView) findViewById(R.id.iv_enemy);

        findViewById(R.id.btn_scissor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_player.setImageResource(R.drawable.ic_scissor);
                String message = lets_play("scissor");
            }
        });
        findViewById(R.id.btn_rock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_player.setImageResource(R.drawable.ic_rock);
                String message = lets_play("rock");
                //Toast.makeText(JankenActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_paper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_player.setImageResource(R.drawable.ic_paper);
                String message = lets_play("paper");
                //Toast.makeText(JankenActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        //ACCELEROMETER
        //inisialisasi vibrator
        vibs = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

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
            jankenDraw();
            vibs.vibrate(100);
            Toast.makeText(JankenActivity.this, "DRAW, TRY AGAIN", Toast.LENGTH_SHORT).show();
            return "Draw";
        }else if ((enemy_janken.equals("rock") && player_janken.equals("scissor")) ||
                  (enemy_janken.equals("scissor") && player_janken.equals("paper")) ||
                  (enemy_janken.equals("paper") && player_janken.equals("rock"))) {
            Intent intent = new Intent(JankenActivity.this, AttackedActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            //finish();
            return "Lose";
        }else if ((enemy_janken.equals("scissor") && player_janken.equals("rock")) ||
                (enemy_janken.equals("rock") && player_janken.equals("paper")) ||
                (enemy_janken.equals("paper") && player_janken.equals("scissor"))){
            Intent intent = new Intent(JankenActivity.this, AttackActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            //finish();
            return "Win";
        }
        return enemy_janken;
    }

    public void jankenDraw(){
//        AlertDialog.Builder attack = new AlertDialog.Builder(JankenActivity.this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View pp_attack = inflater.inflate(R.layout.fragment_attack,null);
//        attack.setView(pp_attack);
//        attack.show();
    }
}
