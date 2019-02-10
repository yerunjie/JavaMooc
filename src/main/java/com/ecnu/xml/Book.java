package com.ecnu.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Created by yerunjie on 2018-12-23
 *
 * @author yerunjie
 */
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
public class Book {

    private int id;
    @XmlAttribute
    private String category;
    @XmlElement
    private String title;
    @XmlElement
    private String author;
    @XmlElement
    private String year;
    @XmlElement
    private BigDecimal price;
}
