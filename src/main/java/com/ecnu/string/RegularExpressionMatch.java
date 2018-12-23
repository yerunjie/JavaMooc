package com.ecnu.string;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionMatch {

    public static final String REGEX_DIGITAL = "[0-9]+";//数字
    public static final String REGEX_ALPHABET = "[a-zA-Z]+";//大小写字母
    public static final String REGEX_SPACE = "\\s+";//空白字符
    public static final String REGEX_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*@[A-Za-z0-9]+(([.\\-])[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";//email

    public static void main(String[] args) {
        List<String> testStrings = new ArrayList<>();
        testStrings.add("12345678901");
        testStrings.add("ABCDEFG");
        testStrings.add("12345678901ABCDEFG");
        testStrings.add("ABC_123");
        testStrings.add(" \r\n\t");
        testStrings.add("12345678901@qq.com");
        testStrings.add("abc_123@163.com");

        testStrings(testStrings, Pattern.compile(REGEX_DIGITAL));
        testStrings(testStrings, Pattern.compile(REGEX_ALPHABET));
        testStrings(testStrings, Pattern.compile(REGEX_SPACE));
        testStrings(testStrings, Pattern.compile(REGEX_EMAIL));

    }

    private static void testStrings(List<String> testStrings, Pattern pattern) {
        System.out.println(pattern.toString());
        for (String testString : testStrings) {
            Matcher matcher = pattern.matcher(testString);
            System.out.println(testString + " : " + matcher.matches());
        }
        System.out.println();
    }
}
