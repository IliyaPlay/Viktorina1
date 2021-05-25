package com.example.viktorina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBQuestions {
    private static final String DATABASE_NAME = "vika.db";
    private static final int DATABASE_VERSION = 1;
    private static final String QUESTIONS_TABLE_NAME = "Questions";

    private static final String QUESTIONS_COLUMN_ID = "id";
    private static final String QUESTIONS_COLUMN_ID_CATEGORY = "ID_Category";
    private static final String QUESTIONS_COLUMN_QUESTION = "Question";
    private static final String QUESTIONS_COLUMN_FALSE_ANSWER_1 = "False_Answer1";
    private static final String QUESTIONS_COLUMN_FALSE_ANSWER_2 = "False_Answer2";
    private static final String QUESTIONS_COLUMN_FALSE_ANSWER_3 = "False_Answer3";
    private static final String QUESTIONS_COLUMN_FALSE_ANSWER_4 = "False_Answer4";
    private static final String QUESTIONS_COLUMN_RIGHT_ANSWER = "Right_Answer";
    private static final String QUESTIONS_COLUMN_SCORE = "Score";

    private static final int NUM_QUESTIONS_COLUMN_ID = 0;
    private static final int NUM_QUESTIONS_COLUMN_ID_CATEGORY = 1;
    private static final int NUM_QUESTIONS_COLUMN_QUESTION = 2;
    private static final int NUM_QUESTIONS_COLUMN_FALSE_ANSWER_1 = 3;
    private static final int NUM_QUESTIONS_COLUMN_FALSE_ANSWER_2 = 4;
    private static final int NUM_QUESTIONS_COLUMN_FALSE_ANSWER_3 = 5;
    private static final int NUM_QUESTIONS_COLUMN_FALSE_ANSWER_4 = 6;
    private static final int NUM_QUESTIONS_COLUMN_RIGHT_ANSWER = 7;
    private static final int NUM_QUESTIONS_COLUMN_SCORE = 8;


    private static final String CATEGORIES_TABLE_NAME = "Categories";

    private static final String CATEGORIES_COLUMN_ID = "id";
    private static final String CATEGORIES_COLUMN_CATEGORY = "Category";

    private static final int NUM_CATEGORIES_COLUMN_ID = 0;
    private static final int NUM_CATEGORIES_COLUMN_CATEGORY = 1;

    private final SQLiteDatabase qDataBase;

    public DBQuestions(Context context) {
        OpenHelper OpenHelper = new OpenHelper(context);
        qDataBase = OpenHelper.getWritableDatabase();
    }

    public long insertQuestion(int id_category, String question, String false_answer_1, String false_answer_2, String false_answer_3, String false_answer_4, String right_answer, int score ) {
        ContentValues cv = new ContentValues();
        cv.put(QUESTIONS_COLUMN_ID_CATEGORY, id_category);
        cv.put(QUESTIONS_COLUMN_QUESTION, question);
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_1, false_answer_1);
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_2, false_answer_2);
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_3, false_answer_3);
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_4, false_answer_4);
        cv.put(QUESTIONS_COLUMN_RIGHT_ANSWER, right_answer);
        cv.put(QUESTIONS_COLUMN_SCORE, score);
        return qDataBase.insert(QUESTIONS_TABLE_NAME, null, cv);
    }

    public long insertCategory(String category) {
        ContentValues cv = new ContentValues();
        cv.put(CATEGORIES_COLUMN_CATEGORY, category);
        return qDataBase.insert(CATEGORIES_TABLE_NAME, null, cv);
    }

    public int update(Questions q) {
        ContentValues cv = new ContentValues();
        cv.put(QUESTIONS_COLUMN_ID, q.getId());
        cv.put(QUESTIONS_COLUMN_QUESTION, q.getQuestion());
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_1, q.getFalse_answer_1());
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_2,q.getFalse_answer_2());
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_3,q.getFalse_answer_3());
        cv.put(QUESTIONS_COLUMN_FALSE_ANSWER_4,q.getFalse_answer_4());
        cv.put(QUESTIONS_COLUMN_RIGHT_ANSWER,q.getRight_answer());
        cv.put(QUESTIONS_COLUMN_SCORE,q.getScore());
        return qDataBase.update(QUESTIONS_TABLE_NAME, cv, QUESTIONS_COLUMN_ID + " = ?",new String[] { String.valueOf(q.getId())});
    }

    public void deleteAll() {
        qDataBase.delete(QUESTIONS_TABLE_NAME, null, null);
    }

    public void delete(long id) {
        qDataBase.delete(QUESTIONS_TABLE_NAME, QUESTIONS_COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Questions select(long id) {
        Cursor qCursor = qDataBase.query(QUESTIONS_TABLE_NAME, null, QUESTIONS_COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        qCursor.moveToFirst();
        String Question = qCursor.getString(NUM_QUESTIONS_COLUMN_QUESTION);
        int IdCategory = qCursor.getInt(NUM_QUESTIONS_COLUMN_ID_CATEGORY);
        String FalseAnswer1 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_1);
        String FalseAnswer2 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_2);
        String FalseAnswer3 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_3);
        String FalseAnswer4 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_4);
        String RightAnswer = qCursor.getString(NUM_QUESTIONS_COLUMN_RIGHT_ANSWER);
        int Score = qCursor.getInt(NUM_QUESTIONS_COLUMN_SCORE);
        return new Questions(id, IdCategory, Question, FalseAnswer1, FalseAnswer2, FalseAnswer3, FalseAnswer4, RightAnswer, Score);
    }

    public int questionCount(){
        Cursor cursor = qDataBase.query(QUESTIONS_TABLE_NAME, new String[]{"Count(*)"}, null, null, null,null,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count;
    }

    public int categoryCount(){
        Cursor cursor = qDataBase.query(CATEGORIES_TABLE_NAME, new String[]{"Count(*)"}, null, null, null,null,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count;
    }

    public ArrayList<Questions> selectAllQuestions() {
        Cursor qCursor = qDataBase.query(QUESTIONS_TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Questions> arr = new ArrayList<Questions>();
        qCursor.moveToFirst();
        if (!qCursor.isAfterLast()) {
            do {
                long id = qCursor.getLong(NUM_QUESTIONS_COLUMN_ID);
                String Question = qCursor.getString(NUM_QUESTIONS_COLUMN_QUESTION);
                int IdCategory = qCursor.getInt(NUM_QUESTIONS_COLUMN_ID_CATEGORY);
                String FalseAnswer1 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_1);
                String FalseAnswer2 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_2);
                String FalseAnswer3 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_3);
                String FalseAnswer4 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_4);
                String RightAnswer = qCursor.getString(NUM_QUESTIONS_COLUMN_RIGHT_ANSWER);
                int Score = qCursor.getInt(NUM_QUESTIONS_COLUMN_SCORE);
                arr.add(new Questions(id, IdCategory, Question, FalseAnswer1, FalseAnswer2, FalseAnswer3, FalseAnswer4, RightAnswer, Score ));
            } while (qCursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<Questions> selectAllQuestionsWithCategory(long categoryId) {
        Cursor qCursor = qDataBase.query(QUESTIONS_TABLE_NAME, null, QUESTIONS_COLUMN_ID_CATEGORY + " = ?", new String[]{String.valueOf(categoryId)}, null, null, null);

        ArrayList<Questions> arr = new ArrayList<Questions>();
        qCursor.moveToFirst();
        if (!qCursor.isAfterLast()) {
            do {
                long id = qCursor.getLong(NUM_QUESTIONS_COLUMN_ID);
                String Question = qCursor.getString(NUM_QUESTIONS_COLUMN_QUESTION);
                int IdCategory = qCursor.getInt(NUM_QUESTIONS_COLUMN_ID_CATEGORY);
                String FalseAnswer1 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_1);
                String FalseAnswer2 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_2);
                String FalseAnswer3 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_3);
                String FalseAnswer4 = qCursor.getString(NUM_QUESTIONS_COLUMN_FALSE_ANSWER_4);
                String RightAnswer = qCursor.getString(NUM_QUESTIONS_COLUMN_RIGHT_ANSWER);
                int Score = qCursor.getInt(NUM_QUESTIONS_COLUMN_SCORE);
                arr.add(new Questions(id, IdCategory, Question, FalseAnswer1, FalseAnswer2, FalseAnswer3, FalseAnswer4, RightAnswer, Score ));
            } while (qCursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<Categories> selectAllCategories() {
        Cursor cCursor = qDataBase.query(CATEGORIES_TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Categories> arr = new ArrayList<Categories>();
        cCursor.moveToFirst();
        if (!cCursor.isAfterLast()) {
            do {
                long id = cCursor.getLong(NUM_CATEGORIES_COLUMN_ID);
                String Category = cCursor.getString(NUM_CATEGORIES_COLUMN_CATEGORY);
                arr.add(new Categories(id, Category));
            } while (cCursor.moveToNext());
        }
        return arr;
    }


    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + QUESTIONS_TABLE_NAME + " (" +
                    QUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    QUESTIONS_COLUMN_ID_CATEGORY+ " INT, " +
                    QUESTIONS_COLUMN_QUESTION+ " TEXT, " +
                    QUESTIONS_COLUMN_FALSE_ANSWER_1 + " TEXT, " +
                    QUESTIONS_COLUMN_FALSE_ANSWER_2 + " TEXT, " +
                    QUESTIONS_COLUMN_FALSE_ANSWER_3 + " TEXT, " +
                    QUESTIONS_COLUMN_FALSE_ANSWER_4 + " TEXT, " +
                    QUESTIONS_COLUMN_RIGHT_ANSWER + " TEXT, " +
                    QUESTIONS_COLUMN_SCORE+" INT);";
            db.execSQL(query);

           query = "CREATE TABLE " + CATEGORIES_TABLE_NAME + " (" +
                    CATEGORIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   CATEGORIES_COLUMN_CATEGORY + " TEXT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE_NAME);
            onCreate(db);
        }
    }
}
