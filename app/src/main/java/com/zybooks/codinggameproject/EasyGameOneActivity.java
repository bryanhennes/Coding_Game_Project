package com.zybooks.codinggameproject;

import static com.zybooks.codinggameproject.RegisterActivity.MYPREF;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.NoRouteToHostException;
import java.util.Arrays;
import java.util.HashMap;


public class EasyGameOneActivity extends AppCompatActivity {

    ImageView iv;
    ImageView leftArrow;
    ImageView rightArrow;
    ImageView upArrow;
    ImageView downArrow;
    ImageView pathImage;
    ImageView pathImage2;
    ImageView playButton;
    ImageView youWinImage;
    Button firstStep;
    Button secondStep;
    Button thirdStep;
    Button nextLevel;
    Button mainMenu;
    TextView currentAttemptsTV;
    final int ANIMATION_DURATION = 1500;
    boolean firstMoveCorrect;
    boolean secondMoveCorrect;
    boolean thirdMoveCorrect;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    int firstPathStartX;
    int firstPathStartY;
    int firstPathEndX;
    int firstPathEndY;
    int secondPathStartX;
    int secondPathStartY;
    int secondPathEndX;
    int secondPathEndY;
    int totalAttempts;

    private ObjectAnimator animatorRight;
    private ObjectAnimator animatorDown;
    final int PATH_LENGTH = 645;
    String answers[] = new String[3];
    String correctAnswers[] = {"right", "down", "right"}; //array of correct sequence to beat game
    String currentAnswer= "";

    MediaPlayer winSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game_one);



        iv = (ImageView) findViewById(R.id.meeseeksIV);
        iv.setX(50);
        leftArrow = (ImageView) findViewById(R.id.arrowLeft);

        //check when user clicks left arrow
        leftArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentAnswer = "left";
                showSteps(v, currentAnswer);
                populateAnswer(v, currentAnswer);
                currentAnswer = "";
            }
        });
        rightArrow = (ImageView) findViewById(R.id.arrowRight);

        //check when user clicks right arrow
        rightArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentAnswer = "right";
                showSteps(v, currentAnswer);
                populateAnswer(v, currentAnswer);
                currentAnswer = "";
            }
        });
        upArrow = (ImageView) findViewById(R.id.arrowUp);

        //check when user clicks up arrow
        upArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentAnswer = "up";
                showSteps(v, currentAnswer);
                populateAnswer(v, currentAnswer);
                currentAnswer = "";
            }
        });
        downArrow = (ImageView) findViewById(R.id.arrowDown);

        //check when user clicks down arrow
        downArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentAnswer = "down";
                showSteps(v, currentAnswer);
                populateAnswer(v, currentAnswer);
                currentAnswer = "";
            }
        });

        firstPathStartX = 50;
        firstPathStartY = -50;
        firstPathEndX = 300;
        firstPathEndY = 200;

        secondPathStartX = 200;
        secondPathStartY = -50;
        secondPathEndX = 200;
        secondPathEndY = 100;

        pathImage = (ImageView) findViewById(R.id.pathIV);
        pathImage.setX(firstPathStartX);
        pathImage.setY(firstPathStartY);
        pathImage2 = (ImageView)findViewById(R.id.pathIV2);

        iv.setX(firstPathStartX);
        iv.setY(250);

        totalAttempts = 1;
        currentAttemptsTV = (TextView) findViewById(R.id.currentAttemptsTV);
        currentAttemptsTV.setText("Total Attempts: "+ totalAttempts);

        playButton = (ImageView) findViewById(R.id.playButton);

        firstStep = (Button) findViewById(R.id.firstStepBox);
        secondStep = (Button) findViewById(R.id.secondStepBox);
        thirdStep = (Button) findViewById(R.id.thirdStepBox);

        youWinImage = (ImageView) findViewById(R.id.youWinImageView);

        nextLevel = (Button) findViewById(R.id.nextLevelButton);
        mainMenu = (Button) findViewById(R.id.mainMenuButton);

        //initialize winning sound
        winSound = MediaPlayer.create(this.getApplicationContext(), R.raw.win_sound);



    }


    //if all directions are correct, move character to the end
    public void moveCharacter(View view) {

        //initalze first animation from left to right
        animatorRight = ObjectAnimator.ofFloat(iv, "translationX", firstPathStartX, PATH_LENGTH);
        animatorRight.setInterpolator(new LinearInterpolator());
        animatorRight.setDuration(ANIMATION_DURATION);
        animatorRight.start();

        //initialize second animation from top to bottom
        animatorDown = ObjectAnimator.ofFloat(iv, "translationY", 350, PATH_LENGTH +227);
        animatorDown.setInterpolator(new LinearInterpolator());
        animatorDown.setDuration(ANIMATION_DURATION);

        //track first animation
        animatorRight.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorDown.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });

        //initalize third animation from left to right to finish
        animatorRight = ObjectAnimator.ofFloat(iv, "translationX", PATH_LENGTH, PATH_LENGTH*2 - 25);
        animatorRight.setInterpolator(new LinearInterpolator());
        animatorRight.setDuration(ANIMATION_DURATION);

        //track second animation
        animatorDown.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorRight.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });

        //track the last animation
        animatorRight.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                clearSteps(view);
                winSound.start();
                youWinImage.setVisibility(View.VISIBLE);
                nextLevel.setVisibility(View.VISIBLE);
                mainMenu.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });

    }


    //check to see if users selected answers match the correct sequence of actions
    //if so, play animation moving character to the end of the path
    public void checkAnswer(View view) {
        //if sequence of answers matches correct sequence, user has won and the animation plays
        if(Arrays.equals(answers, correctAnswers)) {
            moveCharacter(view);
            playButton.setEnabled(false);
        }
        else {
            totalAttempts++;
            currentAttemptsTV.setText("Total Attempts: "+ totalAttempts);
            clearSteps(view);
            Toast.makeText(EasyGameOneActivity.this, "Incorrect", Toast.LENGTH_LONG).show();

        }


    }

    //when player selects an arrow, store that direction name in the array of strings to eventually compare to array of correct answers
    public void populateAnswer(View view, String answer ) {
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == null){
                answers[i] = answer;
                break;
            }
        }
    }

    //display each choice player makes on the right side of screen in form of disabled buttons
    public void showSteps(View view, String answer){
        Button steps[] = {firstStep, secondStep, thirdStep};
        for(int i = 0; i < steps.length; i++){
            if(steps[i].getText() == ""){
                steps[i].setText(answer);
                steps[i].setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    //clear the choices off the right side of the screen when starting a new attempt
    public void clearSteps(View view){
        answers = new String[3];
        Button steps[] = {firstStep, secondStep, thirdStep};
        for(int i = 0; i < steps.length; i++){
            if(steps[i].getText() != ""){
                steps[i].setText("");
                steps[i].setVisibility(View.INVISIBLE);
            }
        }
    }



    //move to next level
    public void moveToNextLevel(View view) {
        Intent intent = new Intent(this, EasyGameTwoActivity.class);
        intent.putExtra("levelOneScore", totalAttempts);
        startActivity(intent);

    }

    //return to main menu
    public void goToMainMenu(View view) {
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
    }

    public void test(View view) {
        startActivity(new Intent(getApplicationContext(), EasyGameTwoActivity.class));
    }
}