package com.ecnu.docx;

import com.aspose.words.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class DocxTest {


    public static boolean getLicense() {
        boolean result = false;
        try {
            License aposeLic = new License();
            aposeLic.setLicense("license.xml");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //getLicense();
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("\\$name\\$", "陈良育");
            map.put("\\$tel\\$", "12345678901");
            Map<String, String> imageMap = new HashMap<String, String>();
            imageMap.put("\\$image\\$", "pic.jpg");

            Document doc = new Document("test.docx");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                doc.getRange().replace(Pattern.compile(entry.getKey()), entry.getValue());
            }
            for (Map.Entry<String, String> entry : imageMap.entrySet()) {
                doc.getRange().replace(Pattern.compile(entry.getKey()), new ReplaceImage(entry.getValue()), false);
            }

            doc.save("123.pdf", SaveFormat.PDF);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

class ReplaceImage implements IReplacingCallback {
    String fileName;

    public ReplaceImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int replacing(ReplacingArgs replacingArgs) throws Exception {
        Node node = replacingArgs.getMatchNode();
        Document document = (Document) node.getDocument();
        DocumentBuilder documentBuilder = new DocumentBuilder(document);
        documentBuilder.moveTo(node);
        documentBuilder.insertImage(fileName);
        return ReplaceAction.REPLACE;
    }
}
