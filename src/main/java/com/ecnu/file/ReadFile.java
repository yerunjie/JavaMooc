package com.ecnu.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

  public static void main(String[] args) {
    File file = new File("file1.txt");
    try (FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader)) {
      String line;
      while ((line = br.readLine()) != null) {
        // 一次读入一行数据
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
