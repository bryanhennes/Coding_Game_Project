package com.zybooks.codinggameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

public class HardGameOneActivity extends AppCompatActivity {

    ImageView iv;
    ImageView leftArrow;
    ImageView rightArrow;
    ImageView upArrow;
    ImageView downArrow;
    ImageView playButton;
    ImageView youWinImage;
    Button firstStep;
    Button secondStep;
    Button thirdStep;
    Button fourthStep;
    Button fifthStep;
    Button sixthStep;
    Button nextLevel;
    Button mainMenu;
    TextView currentAttempts;
    final int ANIMATION_DURATION = 1000;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    int firstPathStartX;
    int firstPathStartY;
    int firstPathEndX;
    int firstPathEndY;
    int secondPathStartX;
    int secondPathStartY;
    int secondPathEndX;
    int secondPathEndY;
    //to do: implement counter that tracks how many attempts each level takes user
    int totalAttempts;

    private ObjectAnimator animatorFirst;
    private ObjectAnimator animatorSecond;
    private ObjectAnimator animatorThird;
    private ObjectAnimator animatorFourth;
    private ObjectAnimator animatorFifth;
    private ObjectAnimator animatorSixth;
    final int PATH_LENGTH = 645;
    String answers[] = new String[6];
    String correctAnswers[] = {"right", "up", "right", "down", "right", "up"}; //array of correct sequence to beat game
    String currentAnswer= "";

    MediaPlayer winSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game_one);

        iv = (ImageView) findViewById(R.id.meeseeksIV);
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


        totalAttempts = 1;
        currentAttempts = (TextView) findViewById(R.id.currentAttemptsTV);
        currentAttempts.setText("Total Attempts: " + totalAttempts);

        playButton = (ImageView) findViewById(R.id.playButton);

        firstStep = (Button) findViewById(R.id.firstStepBox);
        secondStep = (Button) findViewById(R.id.secondStepBox);
        thirdStep = (Button) findViewById(R.id.thirdStepBox);
        fourthStep = (Button)findViewById(R.id.fourthStepBox);
        fifthStep = (Button)findViewById(R.id.fifthStepBox);
        sixthStep = (Button)findViewById(R.id.sixthStepBox);

        youWinImage = (ImageView) findViewById(R.id.youWinImageView);

        nextLevel = (Button) findViewById(R.id.nextLevelButton);
        mainMenu = (Button) findViewById(R.id.mainMenuButton);

        winSound = MediaPlayer.create(this.getApplicationContext(), (R.raw.win_sound));


    }


    //if all directions are correct, move character to the end
    public void moveCharacter(View view) {

        //initialize first animation from top to bottom
        animatorFirst = ObjectAnimator.ofFloat(iv, "translationX", iv.getX(), PATH_LENGTH/2-60);
        animatorFirst.setInterpolator(new LinearInterpolator());
        animatorFirst.setDuration(ANIMATION_DURATION);
        animatorFirst.start();

        //initialize second animation from left to right
        animatorSecond = ObjectAnimator.ofFloat(iv, "translationY", iv.getY(), PATH_LENGTH-255 );
        animatorSecond.setInterpolator(new LinearInterpolator());
        animatorSecond.setDuration(ANIMATION_DURATION);

        //track first animation
        animatorFirst.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                animatorSecond.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });


        //initialize third animation from bottom to top
        animatorThird = ObjectAnimator.ofFloat(iv, "translationX", iv.getX() +(PATH_LENGTH/2-60), (PATH_LENGTH/2-30)*2);
        animatorThird.setInterpolator(new LinearInterpolator());
        animatorThird.setDuration(ANIMATION_DURATION);

        //track second animation
        animatorSecond.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorThird.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });
        animatorFourth = ObjectAnimator.ofFloat(iv, "translationY", iv.getY() -(PATH_LENGTH-300), (PATH_LENGTH-280)*2);
        animatorFourth.setInterpolator(new LinearInterpolator());
        animatorFourth.setDuration(ANIMATION_DURATION);

        //track the last animation
        animatorThird.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorFourth.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });

        animatorFifth = ObjectAnimator.ofFloat(iv, "translationX", 600, (PATH_LENGTH/2)*3-50);
        animatorFifth.setInterpolator(new LinearInterpolator());
        animatorFifth.setDuration(ANIMATION_DURATION);

        animatorFourth.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorFifth.start();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });
        animatorSixth = ObjectAnimator.ofFloat(iv, "translationY", 700, 390 );
        animatorSixth.setInterpolator(new LinearInterpolator());
        animatorSixth.setDuration(ANIMATION_DURATION);

        animatorFifth.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorSixth.start();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }


        });
        animatorSixth.addListener(new Animator.AnimatorListener() {
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
            clearSteps(view);
            totalAttempts++;
            currentAttempts.setText("Total Attempts: " + totalAttempts);
            Toast.makeText(HardGameOneActivity.this, "Incorrect", Toast.LENGTH_LONG).show();

        }
        //test animations

        //moveCharacter(view);

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
        Button steps[] = {firstStep, secondStep, thirdStep, fourthStep, fifthStep, sixthStep};
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
        answers = new String[6];
        Button steps[] = {firstStep, secondStep, thirdStep, fourthStep, fifthStep, sixthStep};
        for(int i = 0; i < steps.length; i++){
            if(steps[i].getText() != ""){
                steps[i].setText("");
                steps[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    //move to next level (if i make a third)
    public void moveToNextLevel(View view) {
        Intent intent = new Intent(this, HardGameTwoActivity.class);
        intent.putExtra("levelOneScore", totalAttempts);
        startActivity(intent);
    }

    //return to main menu
    public void goToMainMenu(View view) {
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
    }

    public void skip(View view) {
        startActivity(new Intent(getApplicationContext(), HardGameTwoActivity.class));
    }
}