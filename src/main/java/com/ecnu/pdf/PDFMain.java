package com.ecnu.pdf;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.Splitter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yerunjie on 2018-12-23
 *
 * @author yerunjie
 */
public class PDFMain {
    public static void main(String[] args) throws Exception {
        File file = new File("data.pdf");
        getText(file);

    }

    private static void getText(File file) throws Exception {
        try (PDDocument doc = PDDocument.load(file)) {
            List pages = doc.getDocumentCatalog().getAllPages();
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                System.out.println("Page:" + i);
                PDFTextStripper stripper = new PDFTextStripper();
                // 设置按顺序输出
                stripper.setSortByPosition(true);
                stripper.setStartPage(0);
                stripper.setEndPage(doc.getNumberOfPages());
                //stripper.setWordSeparator("\t");
                //获取内容
                System.out.println(stripper.getText(doc));
            }
        }
    }

    private static void mergePDF(List<String> sourceFiles, String destinationFileName) throws IOException, COSVisitorException {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        pdfMerger.setDestinationFileName(destinationFileName);
        for (String fileName : sourceFiles) {
            pdfMerger.addSource(fileName);
        }
        pdfMerger.mergeDocuments();
    }

    public static void splitPDF(File file) throws IOException, COSVisitorException {
        /*String[] strings = file.getParent().split(File.separator);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            stringBuilder.append(strings[i]).append(File.separator);
        }*/
        String path = file.getParent();
        PDDocument document = PDDocument.load(file);

        //Instantiating Splitter class
        Splitter splitter = new Splitter();

        //splitting the pages of a PDF document
        List<PDDocument> Pages = splitter.split(document);

        //Creating an iterator
        Iterator<PDDocument> iterator = Pages.listIterator();

        //Saving each page as an individual document
        int i = 1;
        while (iterator.hasNext()) {
            PDDocument pd = iterator.next();
            pd.save(path + File.separator + i + ".pdf");
            pd.close();
            i = i + 1;
        }
        document.close();
    }
}
