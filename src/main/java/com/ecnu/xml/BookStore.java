package com.ecnu.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by yerunjie on 2018-12-23
 *
 * @author yerunjie
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookstore")
@Data
@ToString
public class BookStore {
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    private List<Book> books;
}
