/*
package com.ecnu.admission.service.impl;

import com.aspose.words.*;
import com.ecnu.admission.AdmissionTicket;
import com.ecnu.admission.service.AdmissionService;
import com.ecnu.zxing.QRCodeUtils;
import org.apache.ibatis.io.Resources;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

*/
/**
 * Created by yerunjie on 2019-03-11
 *
 * @author yerunjie
 *//*

public class AsposeDocxTemplateImpl implements AdmissionService {
    @Override
    public byte[] getPDF(AdmissionTicket admissionTicket) {
        Map<String, String> map = new HashMap<>();
        map.put("\\$candidateNumber\\$", admissionTicket.getCandidateNumber());
        map.put("\\$address\\$", admissionTicket.getAddress());
        map.put("\\$name\\$", admissionTicket.getName());
        map.put("\\$gender\\$", admissionTicket.getGender());
        map.put("\\$subject\\$", admissionTicket.getSubject());
        map.put("\\$time\\$", admissionTicket.getTime());
        map.put("\\$seat\\$", admissionTicket.getSeat());
        String resource = "docx_template.docx";
        //FileOutputStream os=new FileOutputStream(new File("/Users/yerunjie/Desktop/admission_ticket.pdf"));
        //ByteArrayOutputStream os = new ByteArrayOutputStream()
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            FileOutputStream os=new FileOutputStream(new File("/Users/yerunjie/Desktop/admission_ticket.pdf"));
            Document doc = new Document(inputStream);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                doc.getRange().replace(Pattern.compile(entry.getKey()), entry.getValue());
            }

            byte[] zxingqrCode = QRCodeUtils.createZxingqrCode(admissionTicket.getIdNumber());
            doc.getRange().replace(Pattern.compile("\\$qrcode\\$"), new ReplaceImage(zxingqrCode), false);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        //return os.toByteArray();
    }

    class ReplaceImage implements IReplacingCallback {
        byte[] data;

        public ReplaceImage(byte[] data) {
            this.data = data;
        }

        @Override
        public int replacing(ReplacingArgs replacingArgs) throws Exception {
            Node node = replacingArgs.getMatchNode();
            Document document = (Document) node.getDocument();
            DocumentBuilder documentBuilder = new DocumentBuilder(document);
            documentBuilder.moveTo(node);
            documentBuilder.insertImage(data);
            return ReplaceAction.REPLACE;
        }
    }
}
*/
