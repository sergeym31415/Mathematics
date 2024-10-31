package com.meshkov.mathematics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonStart;
    private Button buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTasksActivity();
            }
        });
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettingsActivity();
            }
        });
    }


    private void initViews() {
        buttonStart = findViewById(R.id.buttonStart);
        buttonSettings = findViewById(R.id.buttonSettings);
    }

    private void startTasksActivity() {
        Intent intent = TasksActivity.newIntent(this);
        startActivity(intent);
    }

    private void startSettingsActivity() {
        Intent intent = SettingsActivity.newIntent(this);
        startActivity(intent);
    }
}