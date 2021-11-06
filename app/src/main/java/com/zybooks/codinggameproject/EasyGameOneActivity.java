package com.zybooks.codinggameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipDescription;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.net.NoRouteToHostException;


public class EasyGameOneActivity extends AppCompatActivity {

    ImageView iv;
    ImageView leftArrow;
    ImageView rightArrow;
    ImageView upArrow;
    ImageView downArrow;
    ImageView pathImage;
    final int ANIMATION_DURATION = 2000;
    boolean firstMoveCorrect;
    boolean secondMoveCorrect;
    boolean thirdMoveCorrect;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    int firstPathStartX;
    int firstPathStartY;
    int firstPathEndX;
    int firstPathEndY;
    private ObjectAnimator animator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game_one);



        iv = (ImageView) findViewById(R.id.meeseeksIV);
        iv.setY(200);
        iv.setX(50);
        leftArrow = (ImageView) findViewById(R.id.arrowLeft);
        rightArrow = (ImageView) findViewById(R.id.arrowRight);
        upArrow = (ImageView) findViewById(R.id.arrowUp);
        downArrow = (ImageView) findViewById(R.id.arrowDown);

        firstPathStartX = 50;
        firstPathStartY = 200;
        firstPathEndX = 300;
        firstPathEndY = 200;

        pathImage = (ImageView) findViewById(R.id.pathIV);
        //android.view.ViewGroup.LayoutParams layoutParams = pathImage.getLayoutParams();
        pathImage.setX(firstPathStartX);
        pathImage.setY(firstPathStartY);




        iv.setX(firstPathStartX);





    }



    //animate the character from left to right with linear interpolation
    public void moveCharacter(View view) {

        animateCharacter(view);
        animator = ObjectAnimator.ofFloat(iv, "translationX", firstPathStartX, firstPathEndX);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(ANIMATION_DURATION);
        animator.start();



    }


    //method to create animations and play them simultaneously
    public void animateCharacter(View view){
        //create linear animation
        Animation linearAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.linear);

        //create tween animation that spins character
        Animation tweenAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotation);
        //tweenAnimation.setRepeatCount(Animation.INFINITE);
        //tweenAnimation.setRepeatMode(Animation.RESTART);


        //put animations together and play them simultaneously
        AnimationSet s = new AnimationSet(true);
        s.addAnimation(tweenAnimation);
        s.addAnimation(linearAnimation);
        iv.startAnimation(linearAnimation);


    }

    public void setAnimation(View view){

    }


}