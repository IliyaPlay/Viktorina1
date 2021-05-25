package com.example.viktorina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class ActivityQuestion extends AppCompatActivity {
    public static final String CATEGORY_ID_ARG = "CATEGORY_ID";

    private class GetQuestion extends AsyncTask<Void, Void, Questions>{

        private ActivityQuestion activity;
        private long categoryId;

        public GetQuestion(ActivityQuestion activity, long categoryId) {
            this.activity = activity;
            this.categoryId = categoryId;
        }



        @Override
        protected Questions doInBackground(Void... voids) {

            int defaults = 0;
            final Random random = new Random();
            DBQuestions dbQuestions = new DBQuestions(activity.getApplicationContext());
            List<Questions> questionsList = dbQuestions.selectAllQuestionsWithCategory(categoryId);

            return questionsList.get(random.nextInt(questionsList.size()));
        }

        @Override
        protected void onPostExecute(Questions q) {
            activity.showQuestion(q);
        }
    }


    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    private Questions currentQuestion;
    private TextView scoreTv;
    private int score = 0;
    private int maxScore = 0;
    private int countQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        long id = getIntent().getLongExtra(CATEGORY_ID_ARG, -1);
        answerBtn1 = findViewById(R.id.answer_btn1);
        answerBtn2 = findViewById(R.id.answer_btn2);
        answerBtn3 = findViewById(R.id.answer_btn3);
        answerBtn4 = findViewById(R.id.answer_btn4);
        scoreTv = findViewById(R.id.score_tv);
        View.OnClickListener x = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maxScore += currentQuestion.getScore();
                if (((Button)v).getText().equals(currentQuestion.getRight_answer())){
                    score += currentQuestion.getScore();
                    scoreTv.setText("Счёт: " + Integer.toString(score));
                }
                countQuestions++;
                if (countQuestions < 10) {
                    new GetQuestion(ActivityQuestion.this,id).execute();
                }
                if (countQuestions == 10) {
                    Intent i = new Intent(ActivityQuestion.this, ResultActivity.class);
                    i.putExtra("Score", score);
                    i.putExtra("maxScore", maxScore);
                    startActivity(i);
                }
            }
        };
        answerBtn1.setOnClickListener(x);
        answerBtn2.setOnClickListener(x);
        answerBtn3.setOnClickListener(x);
        answerBtn4.setOnClickListener(x);

        scoreTv.setText("Счёт: " + Integer.toString(score));
        new GetQuestion(this, id).execute();

    }



    private void showQuestion(Questions q){
        currentQuestion = q;
        TextView questionTextTV = findViewById(R.id.question_tv);
        questionTextTV.setText(q.getQuestion());
        answerBtn1.setText(q.getFalse_answer_1());
        answerBtn2.setText(q.getFalse_answer_2());
        answerBtn3.setText(q.getFalse_answer_3());
        answerBtn4.setText(q.getFalse_answer_4());

    }
}