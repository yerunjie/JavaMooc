package com.ecnu.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaxXML {

    public static void main(String[] args) {
        File file = new File("books.xml");
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            SaxHandler handler = new SaxHandler("book");
            parser.parse(new FileInputStream(file), handler);

            List<Book> books = handler.getBooks();
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class SaxHandler extends DefaultHandler {
    private List<Book> books = null;
    private Book book;
    private String currentTag = null;
    private String currentValue = null;
    private String nodeName = null;

    public List<Book> getBooks() {
        return books;
    }

    public SaxHandler(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public void startDocument() throws SAXException {
        // TODO 当读到一个开始标签的时候，会触发这个方法
        super.startDocument();

        books = new ArrayList<Book>();
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO 自动生成的方法存根
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        // TODO 当遇到文档的开头的时候，调用这个方法
        super.startElement(uri, localName, name, attributes);

        if (name.equals(nodeName)) {
            book = new Book();
        }
        if (attributes != null && book != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getQName(i).equals("category")) {
                    book.setCategory(attributes.getValue(i));
                }
            }
        }
        currentTag = name;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO 这个方法用来处理在XML文件中读到的内容
        super.characters(ch, start, length);

        if (currentTag != null && book != null) {
            currentValue = new String(ch, start, length);
            if (!currentValue.trim().equals("") && !currentValue.trim().equals("\n")) {
                switch (currentTag) {
                    case "title":
                        book.setTitle(currentValue);
                        break;
                    case "author":
                        book.setAuthor(currentValue);
                        break;
                    case "year":
                        book.setYear(currentValue);
                        break;
                    case "price":
                        book.setPrice(new BigDecimal(currentValue));
                        break;
                }
            }
        }
        currentTag = null;
        currentValue = null;
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        // TODO 在遇到结束标签的时候，调用这个方法
        super.endElement(uri, localName, name);

        if (name.equals(nodeName)) {
            books.add(book);
        }
    }

}