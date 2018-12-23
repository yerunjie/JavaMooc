package com.ecnu.string;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionGroup {

    //被()包裹为需要查找的结构
    public static final String REGEX_TARGET1 = "[0-9]+([A-Z]+)[0-9]+";//查找数字中间的字母
    public static final String REGEX_TARGET2 = "^\\w+@([A-Za-z0-9]+(([.\\-])[A-Za-z0-9]+)*\\.[A-Za-z0-9]+)$";
    public static final String REGEX_TARGET3 = "[0-9]+([A-Z]+)[0-9]+([A-Z]+)[0-9]+";

    public static void main(String[] args) {
        List<String> testStrings = new ArrayList<>();
        testStrings.add("12345678901");
        testStrings.add("ABCDEFG");
        testStrings.add("12345678901ABCDEFG");
        testStrings.add("ABC_123");
        testStrings.add(" \r\n\t");
        testStrings.add("12345678901@qq.com");
        testStrings.add("abc_123@163.com");
        testStrings.add("123ABC123");
        testStrings.add("123ABC123CDE123");

        testSingleGroup(testStrings, Pattern.compile(REGEX_TARGET1));
        testSingleGroup(testStrings, Pattern.compile(REGEX_TARGET2));
        testGroups(testStrings, Pattern.compile(REGEX_TARGET3));

    }

    private static void testSingleGroup(List<String> testStrings, Pattern pattern) {
        System.out.println(pattern.toString());
        for (String testString : testStrings) {
            System.out.print(testString + " ");
            Matcher matcher = pattern.matcher(testString);
            //是否查找到对应字符串
            if (matcher.find()) {
                //group从1开始
                System.out.println("found: " + matcher.group(1));
            } else {
                System.out.println("not found");
            }
        }
        System.out.println();
    }

    private static void testGroups(List<String> testStrings, Pattern pattern) {
        System.out.println(pattern.toString());
        for (String testString : testStrings) {
            System.out.print(testString + " ");
            Matcher matcher = pattern.matcher(testString);
            //是否查找到对应字符串
            List<String> result = new ArrayList<>();
            if (matcher.find()) {
                //多个Group匹配
                int groupCount = matcher.groupCount();
                for (int i = 1; i <= groupCount; i++) {
                    result.add(matcher.group(i));
                }
            }
            if (result.isEmpty()) {
                System.out.println("not found");
            } else {
                System.out.println("found: " + String.join(",", result));
            }
        }
        System.out.println();
    }
}
