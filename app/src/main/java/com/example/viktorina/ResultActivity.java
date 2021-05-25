package com.example.viktorina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    TextView result;
    Button b;
    Button b1;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("Score", 0);
        int maxScore = getIntent().getIntExtra("maxScore", 0);
        result = (TextView) findViewById(R.id.result_tv);
        result.setText("Ваш счёт: " + Integer.toString(score) + "/" + Integer.toString(maxScore));
        b = (Button) findViewById(R.id.result_btn);
        b1 = (Button) findViewById(R.id.close_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


    }
}