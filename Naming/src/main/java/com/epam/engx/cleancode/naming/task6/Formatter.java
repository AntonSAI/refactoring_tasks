package com.epam.engx.cleancode.naming.task6;

public class Formatter {

    private static final String FORMAT_FOR_PLUS = "+";
    private static final String FORMAT_FOR_PIPE = "|";
    private static final String FORMAT_FOR_MINUS = "-";
    private static final String FORMAT_FOR_UNDERSCORE = " _ ";

    public static void main(String[] args) {
        System.out.println(formatKeyValue("enable", "true"));
        System.out.println(formatKeyValue("name", "Bob"));
    }

    private static String formatKeyValue(String key, String value) {
        String content = key + FORMAT_FOR_UNDERSCORE + value;
        String minusRepeat = repeat(FORMAT_FOR_MINUS, content.length());
        return FORMAT_FOR_PLUS +  minusRepeat + FORMAT_FOR_PLUS + "\n"
                + FORMAT_FOR_PIPE + content + FORMAT_FOR_PIPE + "\n" +
                FORMAT_FOR_PLUS + minusRepeat + FORMAT_FOR_PLUS + "\n";
    }

    private static String repeat(String symbol, int times) {
        String result = "";
        for (int i = 0; i < times; i++)
            result += symbol;
        return result;
    }
}
