package com.ecnu.database.mybatis;

import com.ecnu.xml.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {


    @Insert("INSERT INTO `book`(`title`, `author`, `price`, `year`, `category`) " +
            "VALUES (#{title},#{author},#{price},#{year},#{category})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int createBook(Book book);


    @Select("SELECT * FROM book")
    List<Book> getAll();


}
