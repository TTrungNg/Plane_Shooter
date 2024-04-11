package com.example.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WinnerScene extends AppCompatActivity {
    TextView playerScore;
    SharedPreferences sharedPreferences;
    PlayRecord playRecord;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_scene);

        //AnimatedStarsBackground
        LinearLayout linearLayout = findViewById(R.id.winnerScene);
        Animated_Background.startAnimatedBackground(linearLayout);

        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String playerName = sharedPreferences.getString("playerName", "Null");

        int score = getIntent().getExtras().getInt("score");
        playerScore = findViewById(R.id.WinScore);
        playerScore.setText("" + score);

        playRecord = new PlayRecord(System.currentTimeMillis(), playerName, score);

        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
        databaseHelper.addPlayRecord(playRecord);
    }

    public void restart(View view) {
        Intent intent = new Intent(WinnerScene.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {

        Intent intent = new Intent(WinnerScene.this, UserInput.class);
        startActivity(intent);
        finish();
    }
}
