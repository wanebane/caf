package com.rivaldy.caf.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class Fashion {

    private static Logger log = LoggerFactory.getLogger(Fashion.class);

    private static final String fmtPrint = "Total Original Price:$%s;Total sale price:$%s;Amount saved:$%s";
    private static final String symbolPrice = "$";
    private static final String symbolSale = "%";

    public static void main(String[] args) {
        var input1 = "Jacket $34 12%,Hoodie $25 5%,Shirt $12 50%";
        var expected1 = "Total Original Price:$71.00;Total sale price:$59.67;Amount saved:$11.33";
        var actual1 = billingTransaction(input1);
        log.info("Actual Result : {}", actual1);
        log.info("Result 1 : {}", expected1.equals(actual1));

        var input2 = "Pants - $20 -10%,Shorts - $15 -10%";
        var expected2 = "Total Original Price:$35.00;Total sale price:$31.50;Amount saved:$3.50";
        var actual2 = billingTransaction(input2);
        log.info("Actual Result : {}", actual2);
        log.info("Result 2 : {}", expected2.equals(actual2));

        var input3 = "Sweater $36 12%,Shirt 12 50%";
        var expected3 = "Total Original Price:$36.00;Total sale price:$31.68;Amount saved:$4.32";
        var actual3 = billingTransaction(input3);
        log.info("Actual Result : {}", actual3);
        log.info("Result 3 : {}", expected3.equals(actual3));

        var input4 = "Bag $25.50 10%";
        var expected4 = "Total Original Price:$25.50;Total sale price:$22.95;Amount saved:$2.55";
        var actual4 = billingTransaction(input4);
        log.info("Actual Result : {}", actual4);
        log.info("Result 4 : {}", expected4.equals(actual4));
    }

    public static String billingTransaction(String items) {
        var splitItem = items.split(",");

        double totalPrice = 0;
        double salePrice = 0;
        double savedPrice = 0;
        for (String item : splitItem) {
            var itemDesc = item.split(" ");
            var price = getPrice(itemDesc);
            var sale = getSale(itemDesc);
            var amountSaved = getAmountSaved(price, sale);
            totalPrice += price;
            salePrice += price - amountSaved;
            savedPrice += amountSaved;
        }
        return String.format(fmtPrint, convert(totalPrice), convert(salePrice), convert(savedPrice));
    }

    public static double getPrice(String[] descriptions) {
        var indexPrice = 0;
        var price = 0.0;
        for (String desc : descriptions) {
            indexPrice = desc.indexOf(symbolPrice);
            if (indexPrice < 0) {
                continue;
            }
            price = Double.parseDouble(desc.replace(symbolPrice, ""));
        }
        return price;
    }

    public static double getSale(String[] descriptions) {
        var indexSale = 0;
        var sale = 0.0;
        for (String desc : descriptions) {
            indexSale = desc.lastIndexOf(symbolSale);
            if (indexSale < 0) {
                continue;
            }
            sale = Math.abs(Double.parseDouble(desc.replace(symbolSale, "")));
        }
        return sale;
    }

    public static double getAmountSaved(double price, double sale) {
        return price * (sale / 100);
    }

    public static String convert(double price) {
        var decimalFmt = new DecimalFormat("0.00");
        return decimalFmt.format(price);
    }
}
