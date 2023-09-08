package com.rivaldy.caf.logic;

public class ScoreBased {
    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 4, 5};
        int exp1 = 13;
        int act1 = totalScore(input1);
        System.out.printf((fmtPrint) + "%n", 1, act1, exp1 == act1);

        int[] input2 = {17, 19, 21};
        int exp2 = 9;
        int act2 = totalScore(input2);
        System.out.printf((fmtPrint) + "%n", 2, act2, exp2 == act2);

        int[] input3 = {5, 5, 5};
        int exp3 = 15;
        int act3 = totalScore(input3);
        System.out.printf((fmtPrint) + "%n", 3, act3, exp3 == act3);
    }

    private static final String fmtPrint = "Result %s ? %s - %s";

    public static int totalScore(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }
        var totalScore = 0;
        for (int score : input) {
            totalScore += getPoint(score);
        }
        return totalScore;
    }

    public static int getPoint(int number) {
        if (number <= 0) {
            return 0;
        }

        if (number % 5 == 0) {
            return 5;
        } else if (number % 2 != 0) {
            return 3;
        } else {
            return 1;
        }
    }
}
