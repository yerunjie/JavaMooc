package com.ecnu.commonslang3;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yerunjie on 2018-12-29
 *
 * @author yerunjie
 */
public class Commonslang3Main {
    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank(" 123 "));

        System.out.println(StringUtils.difference("ABC","AB"));
        System.out.println(StringUtils.difference("AB","ABC"));
        System.out.println(StringUtils.difference("ABC","CDE"));

        System.out.println(StringUtils.isAllLowerCase("aBC"));
        System.out.println(StringUtils.isAllLowerCase("abc"));

        System.out.println(StringUtils.trimToEmpty(null));
        System.out.println(StringUtils.trimToEmpty("  "));
    }
}
