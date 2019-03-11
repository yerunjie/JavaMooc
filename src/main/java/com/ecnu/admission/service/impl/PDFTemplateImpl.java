package com.ecnu.admission.service.impl;

import com.ecnu.admission.AdmissionTicket;
import com.ecnu.admission.service.AdmissionService;
import com.ecnu.zxing.QRCodeUtils;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;
import org.apache.ibatis.io.Resources;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by yerunjie on 2019-03-11
 *
 * @author yerunjie
 */
public class PDFTemplateImpl implements AdmissionService {
    @Override
    public byte[] getPDF(AdmissionTicket admissionTicket) {
        String resource = "admission_ticket_template.pdf";
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        PdfReader reader = null;
        PdfStamper pdfStamper = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //FileOutputStream os=new FileOutputStream(new File("/Users/yerunjie/Desktop/admission_ticket.pdf"));
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            reader = new PdfReader(inputStream);
            pdfStamper = new PdfStamper(reader, os);
            AcroFields form = pdfStamper.getAcroFields();
            /*BaseFont baseFont = BaseFont
                    .createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            form.addSubstitutionFont(baseFont);*/
            form.setField("candidateNumber", admissionTicket.getCandidateNumber());
            form.setField("address", admissionTicket.getAddress());
            form.setField("name", admissionTicket.getName());
            form.setField("gender", admissionTicket.getGender());
            form.setField("subject", admissionTicket.getSubject());
            form.setField("time", admissionTicket.getTime());
            form.setField("seat", admissionTicket.getSeat());

            pdfStamper.setFormFlattening(true);
            byte[] zxingqrCode = QRCodeUtils.createZxingqrCode(admissionTicket.getIdNumber());
            PushbuttonField ad = form.getNewPushbuttonFromField("qrcode");
            if (ad != null && zxingqrCode != null) {
                ad.setLayout(PushbuttonField.LAYOUT_ICON_ONLY);
                ad.setProportionalIcon(true);
                ad.setImage(Image.getInstance(zxingqrCode));
                form.replacePushbuttonField("qrcode", ad.getField());
            }
            pdfStamper.setFormFlattening(true);
            /*if (pic != null) {
                PdfContentByte overContent = pdfStamper.getOverContent(1);
                PdfDictionary pdfDictionary = reader.getPageN(1);
                PdfObject pdfObject = pdfDictionary.get(new PdfName("MediaBox"));
                PdfArray pdfArray = (PdfArray) pdfObject;
                Image image = Image.getInstance(pic);
                image.setAbsolutePosition(60, 200);
                overContent.addImage(image);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pdfStamper != null) {
                    pdfStamper.close();
                }
                if (reader != null) {
                    reader.close();
                }
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return os.toByteArray();
    }
}
