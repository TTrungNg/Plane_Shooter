package com.example.projectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    Button backBtn;
    TextView name1st;
    TextView name2nd;
    TextView name3rd;
    TextView name4th;
    TextView name5th;

    TextView score1st;
    TextView score2nd;
    TextView score3rd;
    TextView score4th;
    TextView score5th;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_activity);

        //AnimatedStarsBackground
        LinearLayout linearLayout =  findViewById(R.id.lbActivity);
        Animated_Background.startAnimatedBackground(linearLayout);

        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
        List<PlayRecord> topScores = databaseHelper.getTopScores();



        backBtn = findViewById(R.id.backBtn);

        name1st = findViewById(R.id.name1st);
        name2nd = findViewById(R.id.name2nd);
        name3rd = findViewById(R.id.name3rd);
        name4th = findViewById(R.id.name4th);
        name5th = findViewById(R.id.name5th);

        score1st = findViewById(R.id.score1st);
        score2nd = findViewById(R.id.score2nd);
        score3rd = findViewById(R.id.score3rd);
        score4th = findViewById(R.id.score4th);
        score5th = findViewById(R.id.score5th);

        name1st.setText(topScores.get(0).getName());
        score1st.setText(topScores.get(0).getScore());

        /*name2nd.setText(topScores.get(1).getName());
        score2nd.setText(topScores.get(1).getScore());

        name3rd.setText(topScores.get(2).getName());
        score3rd.setText(topScores.get(2).getScore());

        name4th.setText(topScores.get(3).getName());
        score4th.setText(topScores.get(3).getScore());

        name5th.setText(topScores.get(4).getName());
        score5th.setText(topScores.get(4).getScore());*/

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaderboardActivity.this, MainMenuScene.class));
                finish();
            }
        });
    }
}
