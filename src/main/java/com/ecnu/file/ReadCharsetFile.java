package com.ecnu.file;

import java.io.*;

public class ReadCharsetFile {

    public static void main(String[] args) {
        File file = new File("file1.txt");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //省略close
        }
    }
}
