package com.rivaldy.caf.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Newspaper {

    private static Logger log = LoggerFactory.getLogger(Newspaper.class);
    private static final String whiteSpace = " ";

    public static void main(String[] args) {
        String[] input1 = {"break3ing news5:", "1A 1circle is round!"};
        var expected1 = "Breaking News: A Circle Is Round!";
        var result1 = news(input1);
        log.info("Actual 1 : {}", result1);
        log.info("Result 1 : {}", result1.equals(expected1));

        String[] input2 = {"", "Fresh fried fish - fish fresh fried"};
        var expected2 = "Fresh Fried Fish - Fish Fresh Fried";
        var result2 = news(input2);
        log.info("Actual 2 : {}", result2);
        log.info("Result 2 : {}", result2.equals(expected2));

        String[] input3 = {"2Here2 ", "is", " our STRING"};
        var expected3 = "Here Is Our STRING";
        var result3 = news(input3);
        log.info("Actual 3 : {}", result3);
        log.info("Result 3 : {}", result3.equals(expected3));

        String[] input4 = {"123", "eat;sleep;!repeat", "321"};
        var expected4 = "Eat;Sleep;!Repeat";
        var result4 = news(input4);
        log.info("Actual 4 : {}", result4);
        log.info("Result 4 : {}", result4.equals(expected4));
    }

    public static String news(String[] inputs) {
        var result = new StringBuilder();
        for (int i=0; i < inputs.length; i++) {
            var inputRemoveNum = inputs[i].trim().replaceAll("[0-9]", "");
            if (inputRemoveNum.length() == 0) {
                continue;
            }

            var splitCapitalize = Arrays.stream(inputRemoveNum.split(whiteSpace)).map(Newspaper::capitalize).collect(Collectors.joining(" "));
            var delimiters = findDelimiters(inputRemoveNum);
            if (delimiters.size() <= 1) {
                result.append(splitCapitalize);
                if (i <= inputs.length - 1){
                    result.append(whiteSpace);
                }
                continue;
            }

            var sbr = new StringBuilder();
            for (int j = 0; j < delimiters.size(); j++) {
                sbr.append(delimiters.get(j));
                if (j <= delimiters.size() - j - 1) {
                    sbr.append("|");
                }
            }
            var regexDelimiter = sbr.toString();
            var splitByDelimiter = inputRemoveNum.split(regexDelimiter);
            var capitalizeLetter = Arrays.stream(splitByDelimiter).map(Newspaper::capitalize).collect(Collectors.toList());

            var sample = replaceByCapitalizeLetter(inputRemoveNum, capitalizeLetter);
            result.append(sample);
        }
        return result.toString().trim();
    }

    public static String replaceByCapitalizeLetter(String original, List<String> capitalize) {
        var result = "";

        for (String c : capitalize) {
            if (c.length() == 0) {
                continue;
            }
            var indexOri = original.toLowerCase().indexOf(c.toLowerCase());

            var strToBeReplace = result.isEmpty() ? original : result;
            var sb = new StringBuilder(strToBeReplace);
            sb.replace(indexOri, indexOri + c.length(), c);
            result = sb.toString();
        }
        return result;
    }

    public static List<String> findDelimiters(String input) {
        List<String> delimiters = new ArrayList<>();
        var splitChar = input.replaceAll("[a-zA-Z]", "").trim().split("");
        for (String sc : splitChar) {
            if (delimiters.contains(sc)) {
                continue;
            }
            delimiters.add(sc);
        }
        return delimiters;
    }


    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
