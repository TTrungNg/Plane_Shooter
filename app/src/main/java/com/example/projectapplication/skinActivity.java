package com.example.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kotlin.Pair;

public class skinActivity extends AppCompatActivity {
    private TextView skinName;
    private ImageButton nextBtn;
    private Button backButton;
    private ImageView skinImg;
    private Button pickBtn;
    private View currentView;
    private SharedPreferences sharedPreferences;
    private ArrayList<Pair<Integer,String>> skin;
    private int skinNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skin_activity);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = (int) (displayMetrics.widthPixels * 0.75);
        int height = (int) (displayMetrics.heightPixels * 0.9);

        getWindow().setLayout(width, height);


        skinName = findViewById(R.id.ship_name);
        nextBtn= findViewById(R.id.next);
        backButton = findViewById(R.id.back);
        skinImg = findViewById(R.id.skinImg);
        pickBtn = findViewById(R.id.pickBtn);

        skin = new ArrayList<>();
        skin.add(new Pair<>(R.drawable.spaceship5, "Fire Demon"));
        skin.add(new Pair<>(R.drawable.spaceship4, "Star Light"));
        skin.add(new Pair<>(R.drawable.spaceship1, "Peace Man"));
        skinNum = 0;

        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();


        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the current view
                editor.putInt("current skin", skin.get(skinNum).getFirst());
                editor.commit();
                startActivity(new Intent(skinActivity.this, MainMenuScene.class));
                finish();
            }
        });
    }


    public void nextBtn(View v){
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skinNum + 1 == skin.size()) {
                    skinNum = 0;
                } else {
                    skinNum++;
                }

                skinImg.setImageResource(skin.get(skinNum).getFirst());
                skinName.setText(skin.get(skinNum).getSecond());
            }
        });
    }

    public void backBtn(View v){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skinNum = skinNum - 1; // Giảm giá trị của skinNum trước khi kiểm tra điều kiện
                if (skinNum < 0) { // Kiểm tra nếu giá trị mới nhỏ hơn 0
                    skinNum = skin.size() - 1; // Nếu là giá trị âm, gán lại giá trị cuối cùng
                }


                skinImg.setImageResource(skin.get(skinNum).getFirst());
                skinName.setText(skin.get(skinNum).getSecond());
            }
        });
    }
}
