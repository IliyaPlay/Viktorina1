package com.example.viktorina;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

public class FillDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
private final Context context;

    public FillDatabaseAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DBQuestions dbQuestions = new DBQuestions(context);


        if (dbQuestions.categoryCount() != 0) {
            return  null;
        }
        dbQuestions.insertCategory("Математика");
        dbQuestions.insertCategory("Физика");
        dbQuestions.insertCategory("Биология");
        dbQuestions.insertCategory("История");
        dbQuestions.insertCategory("География");
        dbQuestions.insertCategory("Общие");
        dbQuestions.insertQuestion(1, "Сколько будет 2+2?", "2", "6", "7", "4","4",1);
        dbQuestions.insertQuestion(1, "Сколько будет 2+2*2?", "5", "6", "7", "2","6",1);
        dbQuestions.insertQuestion(2, "Какой заряд у электрона?", "положительный", "отрицательный", "никакой", "нейтральный", "отрицательный", 2);
        dbQuestions.insertQuestion(3, "Какой вес самой крупной жемчужины?", "7кг", "8кг", "6кг", "3кг", "6кг", 1);
        dbQuestions.insertQuestion(4, "Какое государство допускало отправку детей по почте, до 1913 года?", "Российская империя", "США", "Франция", "Нигер", "США", 2);
        dbQuestions.insertQuestion(4, "У какой компании было больше денежных средств, чем у американского правительства?", "Apple", "Microsoft", "Yandex", "Samsung", "Apple", 2);
        dbQuestions.insertQuestion(2, "Сколько весит облако?", "300 тонн", "500 тонн", "100 тонн", "200 тонн", "500 тонн", 1);
        dbQuestions.insertQuestion(5, "Какая страна содержит больше авианосцев, чем у всех стран мира?", "Канада", "Россия", "Великобритания", "США", "США", 2);
        dbQuestions.insertQuestion(6, "Какую книгу чаще всего воруют в американских магазинах?", "Библия", "Энциклопедия", "Книга с рецептами", "Сборник рассказов А. С. Пушкина", "Библия", 1);
        dbQuestions.insertQuestion(5, "Какая страна не содержит рек?", "ЮАР", "Каир", "Саудовская Аравия", "Египет", "Саудовская Аравия", 2);
        dbQuestions.insertQuestion(5, "Как называется единственная река в Антаркдиде", "Рейн", "Эльба", "Юкон", "Оникс", "Оникс", 2);
        dbQuestions.insertQuestion(5, "Какое самое большое озеро в Европе?", "Венерн", "Онежское", "Ладожское", "Байкал", "Ладожское", 2);
        dbQuestions.insertQuestion(2, "Каким веществом заполняли термотры до 17 века?", "Коньяком", "Спиртом", "Водой", "Молоком", "Коньяком", 2);
        dbQuestions.insertQuestion(2, "Сколько продолжался самый долгий полёт курицы?", "9 секунд", "54 секунды", "13 секунд", "33 секунды", "13 секунд", 2);
        dbQuestions.insertQuestion(6, "Какой процент из всех людей являются левшами?", "3%", "7%", "25%", "15%", "7%",2);
        dbQuestions.insertQuestion(3, "На какую максимальную глубины способны нырять утки?", "10 метров", "20 метров", "6 метров", "3 метра", "6 метров", 1);
        dbQuestions.insertQuestion(6, "Какой язык самый популярный?", "Английский", "Французский", "Русский", "Китайский", "Китайский", 1);
        return null;







    }
}
