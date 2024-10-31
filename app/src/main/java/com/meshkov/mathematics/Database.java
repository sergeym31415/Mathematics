package com.meshkov.mathematics;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class Database {
    private MySharedPreferences mySharedPreferences;
    private ArrayList<MathProblem> problems = new ArrayList<>();
    private int numOfMathProblems;
    private int numOfItemInMathProblems;
    private int maxItem;
    private boolean resultCanBeNegative;

    public Database(Context contextExt) {
        mySharedPreferences = new MySharedPreferences(contextExt);
        resultCanBeNegative = mySharedPreferences.isResultCanBeNegative();
        numOfItemInMathProblems = mySharedPreferences.getNumOfItemInMathProblems();
        maxItem = mySharedPreferences.getMaxItem();
        numOfMathProblems = mySharedPreferences.getNumOfMathProblems();
        Log.d("Database", "resultCanBeNegative:"+resultCanBeNegative);
        Log.d("Database", "numOfItemInMathProblems:"+numOfItemInMathProblems);
        Log.d("Database", "maxItem:"+maxItem);
        Log.d("Database", "numOfMathProblems:"+numOfMathProblems);
        for (int i = 0; i < numOfMathProblems; i++) {
            MathProblem mathProblem = new MathProblem(numOfItemInMathProblems, resultCanBeNegative, maxItem);
            problems.add(mathProblem);
        }
    }

    public ArrayList<MathProblem> getProblems() {
        return new ArrayList<>(problems);
    }

}
