package com.example.projectapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.splashscreen.SplashScreen;

public class UserInput extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.projectapplication.MESSAGE";
    TextView textView;
    EditText inputText;
    Button submitBtn;
    public static final int TEXT_REQUEST = 1;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        System.out.println("The Player entered the game");
        setContentView(R.layout.user_input);

        //AnimatedStarsBackground
        LinearLayout linearLayout = findViewById(R.id.userInputLayout);
        Animated_Background.startAnimatedBackground(linearLayout);


        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();
        //setContentView(R.layout.main_menu_scene);
        submitBtn = findViewById(R.id.submitBtn);
        inputText = findViewById(R.id.inputText);
        //textView = (TextView) findViewById(R.id.welcome);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInput.this, MainMenuScene.class);
                String message = inputText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);

                editor.putString("playerName", message);
                editor.commit();

                startActivity(intent);
                finish();
            }
        });


    }
}