package com.ecnu.database;

import com.ecnu.xml.Book;

import java.util.List;

/**
 * Created by yerunjie on 2019-02-22
 *
 * @author yerunjie
 */
public interface IBookStore {

    void addBooks(List<Book> books);

    boolean addBook(Book book);

    List<Book> getBooks();
}
