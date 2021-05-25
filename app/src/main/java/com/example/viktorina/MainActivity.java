package com.example.viktorina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FillDatabaseAsyncTask(getApplicationContext()).execute();

        Button b = (Button) this.findViewById(R.id.main_activity_main_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(MainActivity.this, ActivityChooseCategory.class);
                startActivity(i);
            }
        });
    }
}