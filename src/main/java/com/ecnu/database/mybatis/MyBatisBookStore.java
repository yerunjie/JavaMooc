package com.ecnu.database.mybatis;

import com.ecnu.database.IBookStore;
import com.ecnu.xml.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by yerunjie on 2019-02-22
 *
 * @author yerunjie
 */
public class MyBatisBookStore implements IBookStore {

    private static SqlSession session;

    private static SqlSession getSession() {
        if (session == null) {
            String resource = "mybatis-config.xml";  //告诉MyBatis核心配置文件在哪里
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();
        }
        return session;
    }

    private BookMapper bookMapper;

    public MyBatisBookStore() {
        bookMapper = getSession().getMapper(BookMapper.class);
    }

    @Override
    public void addBooks(List<Book> books) {
        for (Book book : books) {
            bookMapper.createBook(book);
        }
    }

    @Override
    public boolean addBook(Book book) {
        return bookMapper.createBook(book) == 1;
    }

    @Override
    public List<Book> getBooks() {
        return bookMapper.getAll();
    }
}
