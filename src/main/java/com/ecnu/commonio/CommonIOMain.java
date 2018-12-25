package com.ecnu.commonio;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by yerunjie on 2018-12-25
 *
 * @author yerunjie
 */
public class CommonIOMain {
    public static void main(String[] args) throws Exception {
        File file = new File("file1.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            List<String> strings = IOUtils.readLines(inputStream, "GBK");
            System.out.println(strings);
        }

        String gbk = FileUtils.readFileToString(file, "GBK");
        System.out.println(gbk);

        System.out.println(FileUtils.getUserDirectoryPath());

        FileUtils.forceMkdir(new File("123"));

    }
}
