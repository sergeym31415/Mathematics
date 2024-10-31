package com.meshkov.mathematics;

import java.util.ArrayList;
import java.util.Random;

public class MathProblem {
    private int numItems;
    private boolean resultCanBeNegative;
    private int maxItem = 10;
    private Random random = new Random();
    private Random randomOperation = new Random();
    private int result = 0;
    private StringBuilder text;
    private StringBuilder textWithoutResult;
    private ArrayList<Integer> items;
    private ArrayList<Integer> operations;

    public MathProblem(int numItems, boolean resultCanBeNegative, int maxItem) {
        this.resultCanBeNegative = resultCanBeNegative;
        this.numItems = numItems;
        this.maxItem = maxItem;
        generate();
        if(!resultCanBeNegative) {
            while (result < 0) {
                generate();
            }
        }

    }

    public int getNumItems() {
        return numItems;
    }

    public int getResult() {
        return result;
    }

    public StringBuilder getText() {
        return text;
    }

    public StringBuilder getTextWithoutResult() {
        return textWithoutResult;
    }

    private void generate() {
        items = new ArrayList<>();
        operations = new ArrayList<>();
        text = new StringBuilder();
        textWithoutResult = new StringBuilder();
        for (int i = 0; i < numItems; i++) {
            items.add(random.nextInt(maxItem));
        }
        Operation[] operationValues = Operation.values();
        for (Operation operation : operationValues
        ) {
            System.out.println(operation);
        }
        for (int i = 0; i < numItems - 1; i++) {
            operations.add(randomOperation.nextInt(operationValues.length));
        }

        result = items.get(0);
        textWithoutResult.append(result);
        text.append(result);
        for (int i = 1; i < numItems; i++) {
            int codeOperation = operations.get(i - 1);
            Operation operation = operationValues[codeOperation];

            switch (operation) {
                case MINUS:
                    result -= items.get(i);
                    text.append(" - " + items.get(i));
                    textWithoutResult.append(" - " + items.get(i));
                    break;
                case PLUS:
                    result += items.get(i);
                    text.append(" + " + items.get(i));
                    textWithoutResult.append(" + " + items.get(i));
                    break;
            }
        }
        text.append(" = " + result);
        textWithoutResult.append(" = ");

    }
}
