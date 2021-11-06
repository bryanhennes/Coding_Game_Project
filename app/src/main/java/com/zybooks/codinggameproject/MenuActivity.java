package com.zybooks.codinggameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //bring player to easy game mode
    public void startEasyGame(View view) {
        startActivity(new Intent(getApplicationContext(), EasyGameOneActivity.class));
    }

    //bring player to hard game mode
    public void startHardGame(View view) {
    }
}