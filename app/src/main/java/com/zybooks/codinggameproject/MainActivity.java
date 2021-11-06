package com.zybooks.codinggameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //bring player to the menu
    public void startGame(View view) {
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
    }
}