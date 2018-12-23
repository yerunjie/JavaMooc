package com.ecnu.xlsx;

import com.ecnu.xml.Book;
import com.ecnu.xml.BookStore;
import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yerunjie on 2018-12-23
 *
 * @author yerunjie
 */
public class POITest {
    public static void main(String[] args) throws Exception {
        read(new File("books.xlsx"));
    }

    public static void read(File file) throws Exception {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            BookStore bookStore = new BookStore();
            int lastRowNum = sheet.getLastRowNum();
            List<Book> books = new ArrayList<>();
            for (int i = 1; i < lastRowNum; i++) {
                Row row = sheet.getRow(i);
                Book book = new Book();
                int colIndex = 1;
                Cell cell = row.getCell(colIndex++);
                book.setCategory(cell.getStringCellValue());

                cell = row.getCell(colIndex++);
                book.setTitle(cell.getStringCellValue());

                cell = row.getCell(colIndex++);
                book.setAuthor(cell.getStringCellValue());

                cell = row.getCell(colIndex++);
                book.setYear(cell.getStringCellValue());

                cell = row.getCell(colIndex++);
                book.setPrice(new BigDecimal(cell.getNumericCellValue()));

                books.add(book);
            }
            bookStore.setBooks(books);
            System.out.println(bookStore);
        }


    }

    public static void create() throws Exception {
        //生成Workbook对象
        Workbook workbook = new XSSFWorkbook();
        //生成sheet
        Sheet sheet = workbook.createSheet();

        File file = new File("books.json");

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            BookStore bookStore = gson.fromJson(reader, BookStore.class);

            int rolIndex = 1;//行数
            for (Book book : bookStore.getBooks()) {
                Row row = sheet.createRow(rolIndex++);

                int colIndex = 1;
                Cell cell = row.createCell(colIndex++);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(book.getCategory());

                cell = row.createCell(colIndex++);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(book.getTitle());

                cell = row.createCell(colIndex++);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(book.getAuthor());

                cell = row.createCell(colIndex++);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(book.getYear());

                cell = row.createCell(colIndex++);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(book.getPrice().doubleValue());
            }

        }

        file = new File("output.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        }
    }
}
