package com.meshkov.mathematics;

import static androidx.core.content.ContextCompat.getString;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class MySharedPreferences {
    private Context context;
    private SharedPreferences sharedPreferences;

    private boolean resultCanBeNegative;
    private int numOfItemInMathProblems;
    private int maxItem;
    private int numOfMathProblems;

    public MySharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        resultCanBeNegative = sharedPreferences.getBoolean("resultCanBeNegative", false);
        numOfItemInMathProblems = Integer.parseInt(sharedPreferences.getString("numOfItemInMathProblems", "2"));
        maxItem = Integer.parseInt(sharedPreferences.getString("maxItem", "10"));
        numOfMathProblems = Integer.parseInt(sharedPreferences.getString("numOfMathProblems", "15"));
    }

    public boolean isResultCanBeNegative() {
        return resultCanBeNegative;
    }

    public int getNumOfItemInMathProblems() {
        return numOfItemInMathProblems;
    }

    public int getMaxItem() {
        return maxItem;
    }

    public int getNumOfMathProblems() {
        return numOfMathProblems;
    }
}
