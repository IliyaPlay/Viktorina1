package com.example.viktorina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ActivityChooseCategory extends AppCompatActivity {
    private static class GetAllCategories extends AsyncTask<Void, Void, List<Categories>>{

        private ActivityChooseCategory activity;

        public GetAllCategories(ActivityChooseCategory activity) {
            this.activity = activity;
        }

        @Override
        protected List<Categories> doInBackground(Void... voids) {
            DBQuestions dbQuestions = new DBQuestions(activity.getApplicationContext());
            ArrayList<Categories> categories = dbQuestions.selectAllCategories();
            return categories;
        }

        @Override
        protected void onPostExecute(List<Categories> categories) {
            activity.showCategories(categories);
        }
    }

    private class MyAdapter extends BaseAdapter {
        private final List<Categories> data = new ArrayList<>();
        LayoutInflater lInflater;
        public MyAdapter(Context context) {
            lInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        public void setData(List<Categories> categories) {
            data.clear();
            data.addAll(categories);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return data.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = lInflater.inflate(R.layout.categoryitem, parent, false);
            }
            Button b = (Button) view;
            Categories c = data.get(position);
            b.setText(c.getCategory());
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i;
                    i = new Intent(ActivityChooseCategory.this, ActivityQuestion.class);
                    i.putExtra(ActivityQuestion.CATEGORY_ID_ARG, c.getId());
                    startActivity(i);

                }
            });
            return view;
        }
    }

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);


        adapter = new MyAdapter(this);
        GridView gridView = findViewById(R.id.categories_gv);
        gridView.setAdapter(adapter);
        new GetAllCategories(this).execute();

    }

    private void showCategories(List<Categories> categories){
        adapter.setData(categories);

    }
}