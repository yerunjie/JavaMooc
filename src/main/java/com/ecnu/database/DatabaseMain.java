package com.ecnu.database;

import com.ecnu.database.mybatis.MyBatisBookStore;
import com.ecnu.xml.Book;

import java.util.List;

/**
 * Created by yerunjie on 2019-02-10
 *
 * @author yerunjie
 */
public class DatabaseMain {

    public static void main(String[] args) {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载驱动！");
        } catch (ClassNotFoundException e1) {
            System.out.println("找不到驱动!");
            e1.printStackTrace();
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            IBookStore bookStore = new MyBatisBookStore();
            List<Book> books = bookStore.getBooks();
            System.out.println(books);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
