package com.ecnu.json;

import com.ecnu.xml.Book;
import com.ecnu.xml.BookStore;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yerunjie on 2018-12-23
 *
 * @author yerunjie
 */
public class JsonTest {
    public static void main(String[] args) {
        File file = new File("books.json");
        testGson(file);
        testJson(file);
    }

    public static void testJson(File file) {
        JSONObject object = new JSONObject();
        //string
        object.put("string", "string");
        //int
        object.put("int", 2);
        //boolean
        object.put("boolean", true);
        //array
        List<Integer> integers = Arrays.asList(1, 2, 3);
        object.put("list", integers);
        //null
        //object.put("null", null);
        System.out.println(object);
        try (FileReader reader = new FileReader(file)) {
            int fileLen = (int) file.length();
            char[] chars = new char[fileLen];
            reader.read(chars);
            String s = String.valueOf(chars);
            JSONObject jsonObject = new JSONObject(s);
            JSONArray books = jsonObject.getJSONArray("books");
            List<Book> bookList = new ArrayList<>();
            for (Object book : books) {
                JSONObject bookObject = (JSONObject) book;
                Book book1 = new Book();
                book1.setAuthor(bookObject.getString("author"));
                book1.setYear(bookObject.getString("year"));
                book1.setTitle(bookObject.getString("title"));
                book1.setPrice(bookObject.getBigDecimal("price"));
                book1.setCategory(bookObject.getString("category"));
                bookList.add(book1);
            }
            BookStore bookStore = new BookStore();
            bookStore.setBooks(bookList);
            System.out.println(bookStore);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testGson(File file) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            BookStore bookStore = gson.fromJson(reader, BookStore.class);
            System.out.println(bookStore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
