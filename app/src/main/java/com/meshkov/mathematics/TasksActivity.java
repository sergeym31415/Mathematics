package com.meshkov.mathematics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TasksActivity extends AppCompatActivity {
    private Button buttonCheck;
    private LinearLayout linearLayoutTasks;
    private Database database;

    private long startDate;

    private void initViews() {
        linearLayoutTasks = findViewById(R.id.linearLayoutTasks);
        buttonCheck = findViewById(R.id.buttonCheck);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
       // database = new Database(getApplicationContext());
        initViews();
       // showTasks();
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCheckButton();
            }
        });

    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TasksActivity.class);
        return intent;
    }

    private void onClickCheckButton() {
        ArrayList<MathProblem> tasks = database.getProblems();
        int rightTasks = 0;
        int wrongTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            EditText editText = (EditText) (linearLayoutTasks.getChildAt(i)).findViewById(R.id.editTextResult);
            String result = editText.getText().toString().trim();
            String rightResult = String.valueOf(tasks.get(i).getResult());
            if (result.isEmpty()) {
                editText.setBackgroundColor(getColor(R.color.orange));
                wrongTasks++;
            } else if (result.equals(rightResult)) {
                editText.setBackgroundColor(getColor(R.color.green));
                rightTasks++;
            } else {
                editText.setBackgroundColor(getColor(R.color.red));
                wrongTasks++;
            }
        }
        Date endDate = Calendar.getInstance().getTime();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(endDate.getTime() - startDate);
        String textToast = getString(R.string.correct_answers_s_out_of_s);
        textToast = String.format(textToast, rightTasks, rightTasks + wrongTasks, seconds);
        Toast toast = Toast.makeText(this,
                textToast,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        toast.show();

    }

    private void showTasks() {
        for (MathProblem mathProblem : database.getProblems()) {
            View view = getLayoutInflater().inflate(R.layout.task_item,
                    linearLayoutTasks,
                    false);
            TextView textViewTask = view.findViewById(R.id.textViewTask);
            textViewTask.setText(mathProblem.getTextWithoutResult());
            EditText editText = view.findViewById(R.id.editTextResult);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    editText.setBackgroundColor(getColor(R.color.orange));
                }
            });
            linearLayoutTasks.addView(view);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        database = new Database(getApplicationContext());
        showTasks();
        startDate = Calendar.getInstance().getTime().getTime();
    }
}