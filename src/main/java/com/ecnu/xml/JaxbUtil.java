package com.ecnu.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JaxbUtil {

    public static void convertToXml(Object obj, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            //格式化输出，即按标签自动换行，否则就是一行输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //设置编码（默认编码就是utf-8）
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //是否省略xml头信息，默认不省略（false）
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            //文件输出
            //marshaller.marshal(obj, file);
            //控制台输出
            marshaller.marshal(obj, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T> T convertToJavaBean(Class<T> clz, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T t = (T) unmarshaller.unmarshal(file);
            return t;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        File file = new File("books.xml");
        BookStore bookStore = convertToJavaBean(BookStore.class, file);
        System.out.println(bookStore);

        BookStore newBookStore = new BookStore();
        List<Book> books = new ArrayList<>();
        newBookStore.setBooks(books);

        Book book = new Book();
        books.add(book);
        book.setCategory("ABC");
        book.setTitle("title123");
        book.setYear("2018");
        book.setAuthor("陈良育");
        book.setPrice(new BigDecimal("123.45"));
        convertToXml(newBookStore, null);

    }
}