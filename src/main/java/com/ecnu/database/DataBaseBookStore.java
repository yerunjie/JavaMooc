package com.ecnu.database;

import com.ecnu.xml.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yerunjie on 2019-02-10
 *
 * @author yerunjie
 */
public abstract class DataBaseBookStore {

    abstract Connection getConnection() throws SQLException;

    public List<Book> getBooks() {
        try {
            List<Book> books = new ArrayList<>();
            String selectSql = "SELECT * FROM book";
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    Book book = getBook(resultSet);
                    books.add(book);
                }
                return books;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addBooks(List<Book> books) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String insertSql = "INSERT INTO `book`(`title`, `author`, `price`, `year`, `category`) VALUES (?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                for (Book book : books) {
                    preparedStatement.setString(1, book.getTitle());
                    preparedStatement.setString(2, book.getAuthor());
                    preparedStatement.setBigDecimal(3, book.getPrice());
                    preparedStatement.setString(4, book.getYear());
                    preparedStatement.setString(5, book.getCategory());
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addBook(Book book) {
        try {
            Connection connection = getConnection();
            String insertSql = "INSERT INTO `book`(`title`, `author`, `price`, `year`, `category`) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setBigDecimal(3, book.getPrice());
            preparedStatement.setString(4, book.getYear());
            preparedStatement.setString(5, book.getCategory());
            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Book getBook(ResultSet resultSet) {
        Book book = new Book();
        try {
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setYear(resultSet.getString("year"));
            book.setPrice(resultSet.getBigDecimal("price"));
            book.setAuthor(resultSet.getString("author"));
            book.setCategory(resultSet.getString("category"));
            return book;
        } catch (Exception e) {
            throw new RuntimeException("解析出错");
        }
    }
}
