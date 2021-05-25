package com.example.viktorina;

import java.io.Serializable;

public class Questions implements Serializable {
    private final long id;
    private final long id_category;
    private final String question;
    private final String false_answer_1;
    private final String false_answer_2;
    private final String false_answer_3;
    private final String false_answer_4;
    private final String right_answer;
    private final int score;

    public Questions (long id, long id_category, String question, String fa1, String fa2, String fa3, String fa4, String ra, int score) {
        this.id = id;
        this.id_category = id_category;
        this.question = question;
        this.false_answer_1 = fa1;
        this.false_answer_2 = fa2;
        this.false_answer_3 = fa3;
        this.false_answer_4 = fa4;
        this.right_answer = ra;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public long getId_category() {
        return id_category;
    }

    public String getQuestion() {
        return question;
    }

    public String getFalse_answer_1() {
        return false_answer_1;
    }

    public String getFalse_answer_2() {
        return false_answer_2;
    }

    public String getFalse_answer_3() {
        return false_answer_3;
    }

    public String getFalse_answer_4() {
        return false_answer_4;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public int getScore() {
        return score;
    }
}


