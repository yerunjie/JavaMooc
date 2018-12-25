package com.ecnu.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yerunjie on 2018-12-25
 *
 * @author yerunjie
 */
public class GuavaMain {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        System.out.println(list);
        List<Integer> integers = Lists.newArrayList(123, 456);
        System.out.println(integers);

        Iterable<String> split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("123,321,,   abc");

        for (String s : split) {
            System.out.println(s);
        }
        File file = new File("books.json");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = ByteStreams.toByteArray(inputStream);
            String s = new String(bytes);
            System.out.println(s);
        }
    }
}
