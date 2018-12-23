package com.ecnu.xml;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by yerunjie on 2018-12-23
 *
 * @author yerunjie
 */
@Data
@ToString
public class Book {
    private String category;
    private String title;
    private String lang;
    private String author;
    private String year;
    private BigDecimal price;
}
