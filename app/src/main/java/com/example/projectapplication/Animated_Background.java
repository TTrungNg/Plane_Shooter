package com.example.projectapplication;

import android.graphics.drawable.AnimationDrawable;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;


public class Animated_Background {
    //AnimatedStarsBackground
    public static void startAnimatedBackground(LinearLayout linearLayout) {
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.start();
    }
}
