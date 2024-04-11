package com.example.projectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuScene extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_scene);
        Intent intent = getIntent();
        String message = intent.getStringExtra(UserInput.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.namePlayer);
        textView.setText(message);

        //AnimatedStarsBackground
        LinearLayout linearLayout =  findViewById(R.id.mainMenuScene);
        Animated_Background.startAnimatedBackground(linearLayout);




    }

    public void startGame(View view) {

        startActivity(new Intent(this, MainActivity.class));
        Intent intent = getIntent();
        String message = intent.getStringExtra(UserInput.EXTRA_MESSAGE);
        System.out.println(message+" entered the game");
        finish();
    }

    public void skinPopup(View view){
        startActivity(new Intent(this, skinActivity.class));
    }

    public void leaderboard(View view){
        startActivity(new Intent(this, LeaderboardActivity.class));
    }

    public void abtUsBtn(View view){
        startActivity(new Intent(this, abtUsActivity.class));

    }

}
