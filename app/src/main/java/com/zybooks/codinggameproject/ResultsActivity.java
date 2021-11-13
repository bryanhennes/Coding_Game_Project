package com.zybooks.codinggameproject;

import static com.zybooks.codinggameproject.RegisterActivity.MYPREF;
import static com.zybooks.codinggameproject.RegisterActivity.USERNAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {
    int easyLevelOneAttempts;
    int easyLevelTwoAttempts;
    int hardLevelOneAttempts;
    int hardLevelTwoAttempts;

    TextView easyLevelOneTV;
    TextView easyLevelTwoTV;
    TextView hardLevelOneTV;
    TextView hardLevelTwoTV;
    TextView title;

    SharedPreferences sharedPreferences;
    int savedScoreOne;
    int savedScoreTwo;
    int savedScoreThree;
    int savedScoreFour;
    public static final String EASY_LEVEL_ONE_HIGHSCORE = "EASY_LEVEL_ONE_HIGHSCORE_KEY";
    public static final String EASY_LEVEL_TWO_HIGHSCORE = "EASY_LEVEL_TWO_HIGHSCORE_KEY";
    public static final String HARD_LEVEL_ONE_HIGHSCORE = "HARD_LEVEL_ONE_HIGHSCORE_KEY";
    public static final String HARD_LEVEL_TWO_HIGHSCORE = "HARD_LEVEL_TWO_HIGHSCORE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent recInt = getIntent();
        easyLevelOneAttempts = recInt.getIntExtra("levelOneScore", easyLevelOneAttempts);

        Intent recInt2 = getIntent();
        easyLevelTwoAttempts = recInt2.getIntExtra("levelTwoScore", easyLevelTwoAttempts);

        Intent recInt3 = getIntent();
        hardLevelOneAttempts = recInt3.getIntExtra("levelOneScore", hardLevelOneAttempts);

        Intent recInt4 = getIntent();
        hardLevelTwoAttempts = recInt4.getIntExtra("levelTwoScore", hardLevelTwoAttempts);

        easyLevelOneTV = (TextView) findViewById(R.id.easyLevelOneScoreTV);
        easyLevelTwoTV = (TextView) findViewById(R.id.easyLevelTwoScoreTV);
        hardLevelOneTV = (TextView) findViewById(R.id.hardLevelOneScoreTV);
        hardLevelTwoTV = (TextView) findViewById(R.id.hardLevelTwoScoreTV);
        title = (TextView) findViewById(R.id.resultsTextView);



        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        title.setText(sharedPreferences.getString(USERNAME, "" ) + "'s Best Scores!");

        checkHighScoreEasyOne();
        checkHighScoreEasyTwo();
        checkHighScoreHardOne();
        checkHighScoreHardTwo();

        easyLevelOneTV.setText("Easy Level One Best Score: " + sharedPreferences.getInt(EASY_LEVEL_ONE_HIGHSCORE, easyLevelOneAttempts));
        easyLevelTwoTV.setText("Easy Level Two Best Score: " + sharedPreferences.getInt(EASY_LEVEL_TWO_HIGHSCORE, easyLevelTwoAttempts));
        hardLevelOneTV.setText("Hard Level One Best Score: " + sharedPreferences.getInt(HARD_LEVEL_ONE_HIGHSCORE, hardLevelOneAttempts));
        hardLevelTwoTV.setText("Hard Level Two Best Score: " + sharedPreferences.getInt(HARD_LEVEL_TWO_HIGHSCORE, hardLevelTwoAttempts));


    }

    public void checkHighScoreEasyOne(){
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!sharedPreferences.contains(EASY_LEVEL_ONE_HIGHSCORE)){
            editor.putInt(EASY_LEVEL_ONE_HIGHSCORE, easyLevelOneAttempts);
            editor.commit();
            editor.apply();
        }
        else{
            savedScoreOne = sharedPreferences.getInt(EASY_LEVEL_ONE_HIGHSCORE, easyLevelOneAttempts);
            if(easyLevelOneAttempts < savedScoreOne){
                editor.putInt(EASY_LEVEL_ONE_HIGHSCORE, easyLevelOneAttempts);
                editor.commit();
                editor.apply();
            }
        }

    }

    public void checkHighScoreEasyTwo(){
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!sharedPreferences.contains(EASY_LEVEL_TWO_HIGHSCORE)){
            editor.putInt(EASY_LEVEL_TWO_HIGHSCORE, easyLevelTwoAttempts);
            editor.commit();
            editor.apply();
        }
        else{
            savedScoreTwo = sharedPreferences.getInt(EASY_LEVEL_TWO_HIGHSCORE, easyLevelTwoAttempts);
            if(easyLevelTwoAttempts < savedScoreTwo){
                editor.putInt(EASY_LEVEL_TWO_HIGHSCORE, easyLevelTwoAttempts);
                editor.commit();
                editor.apply();
            }
        }

    }

    public void checkHighScoreHardOne(){
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!sharedPreferences.contains(HARD_LEVEL_ONE_HIGHSCORE)){
            editor.putInt(HARD_LEVEL_ONE_HIGHSCORE, hardLevelOneAttempts);
            editor.commit();
            editor.apply();
        }
        else{
            savedScoreThree = sharedPreferences.getInt(HARD_LEVEL_ONE_HIGHSCORE, hardLevelOneAttempts);
            if(hardLevelOneAttempts < savedScoreThree){
                editor.putInt(HARD_LEVEL_ONE_HIGHSCORE, hardLevelOneAttempts);
                editor.commit();
                editor.apply();
            }
        }

    }

    public void checkHighScoreHardTwo(){
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!sharedPreferences.contains(HARD_LEVEL_TWO_HIGHSCORE)){
            editor.putInt(HARD_LEVEL_TWO_HIGHSCORE, hardLevelTwoAttempts);
            editor.commit();
            editor.apply();
        }
        else{
            savedScoreFour = sharedPreferences.getInt(HARD_LEVEL_TWO_HIGHSCORE, hardLevelTwoAttempts);
            if(hardLevelTwoAttempts < savedScoreFour){
                editor.putInt(HARD_LEVEL_TWO_HIGHSCORE, hardLevelTwoAttempts);
                editor.commit();
                editor.apply();
            }
        }

    }

    public void restartGame(View view) {
        startActivity(new Intent(this.getApplicationContext(), MenuActivity.class));
    }
}