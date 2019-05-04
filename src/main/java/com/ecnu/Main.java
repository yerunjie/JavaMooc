package com.ecnu;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yerunjie on 2018-12-23
 *
 * @author yerunjie
 */
public class Main {
    public static void main(String[] args) {

        String domain = "http://faculty.ecnu.edu.cn/search/teacherMain.faces?siteId=10&pageId=0";
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(domain)
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                String text = response.body().string();
                Pattern pattern = Pattern.compile("[\\s\\w]*([^ -~]*学院)");
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    System.out.println((matcher.group(1)));
                }
                //System.out.println(matcher.replaceAll("result"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
