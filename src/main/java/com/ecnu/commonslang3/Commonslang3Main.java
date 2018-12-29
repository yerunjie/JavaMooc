package com.ecnu.commonslang3;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.similarity.EditDistance;
import org.apache.commons.text.similarity.JaccardDistance;
import org.apache.commons.text.similarity.LongestCommonSubsequenceDistance;

import java.io.FileInputStream;

/**
 * Created by yerunjie on 2018-12-29
 *
 * @author yerunjie
 */
public class Commonslang3Main {
    public static void main(String[] args) throws Exception {
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank(" 123 "));

        System.out.println(StringUtils.difference("ABC", "AB"));
        System.out.println(StringUtils.difference("AB", "ABC"));
        System.out.println(StringUtils.difference("ABC", "CDE"));

        System.out.println(StringUtils.isAllLowerCase("aBC"));
        System.out.println(StringUtils.isAllLowerCase("abc"));

        System.out.println(StringUtils.trimToEmpty(null));
        System.out.println(StringUtils.trimToEmpty("  "));

        System.out.println(CaseUtils.toCamelCase("string_new_old", false, '_'));
        String json = IOUtils.toString(new FileInputStream("books.json"));
        System.out.println(json);
        System.out.println(StringEscapeUtils.escapeJson(json));
        EditDistance editDistance = new LongestCommonSubsequenceDistance();
        System.out.println(editDistance.apply("ABCD", "AB"));
    }
}
