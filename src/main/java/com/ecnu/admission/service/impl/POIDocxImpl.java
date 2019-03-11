package com.ecnu.admission.service.impl;

import com.ecnu.admission.AdmissionTicket;
import com.ecnu.admission.service.AdmissionService;
import com.ecnu.zxing.QRCodeUtils;
import org.apache.ibatis.io.Resources;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yerunjie on 2019-03-11
 *
 * @author yerunjie
 */
public class POIDocxImpl implements AdmissionService {
    @Override
    public byte[] getPDF(AdmissionTicket admissionTicket) {
        Map<String, Object> params = new HashMap<>();
        params.put("${can}", admissionTicket.getCandidateNumber());
        params.put("${add}", admissionTicket.getAddress());
        params.put("${name}", admissionTicket.getName());
        params.put("${gender}", admissionTicket.getGender());
        params.put("${subject}", admissionTicket.getSubject());
        params.put("${time}", admissionTicket.getTime());
        params.put("${seat}", admissionTicket.getSeat());
        String resource = "docx_template.docx";
        Map<String, ImageData> picParams = new HashMap<>();//图片类 key-url
        byte[] zxingqrCode = QRCodeUtils.createZxingqrCode(admissionTicket.getIdNumber());
        ImageData qrcode = new ImageData();
        qrcode.setData(zxingqrCode);
        picParams.put("${pic}", qrcode);
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            FileOutputStream os = new FileOutputStream(new File("/Users/yerunjie/Desktop/admission_ticket.pdf"));
            XWPFDocument doc = new XWPFDocument(inputStream);//导入模板文件
            List<IBodyElement> ibes = doc.getBodyElements();
            for (IBodyElement ib : ibes) {
                if (ib.getElementType() == BodyElementType.TABLE) {
                    replaceTable(ib, params, picParams, doc);
                }
            }

            PdfOptions options = PdfOptions.create();
            // 中文字体处理
            /*options.fontProvider(new IFontProvider() {

                @Override
                public Font getFont(String familyName, String encoding, float size, int style, java.awt.Color color) {
                    try {
                        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                        Font fontChinese = new Font(bfChinese);
                        if (familyName != null)
                            fontChinese.setFamily(familyName);
                        return fontChinese;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });*/

            PdfConverter.getInstance().convert(doc, os, options);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    /**
     * 写入image
     *
     * @param run
     * @param data
     * @param doc
     * @throws InvalidFormatException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void replacePic(XWPFRun run, ImageData data, XWPFDocument doc) throws Exception {
        int format = Document.PICTURE_TYPE_PNG;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getData());
        run.addPicture(inputStream, format, "rpic", Units.toEMU(100), Units.toEMU(100));
    }

    /**
     * 替换表格内占位符
     *
     * @param para      表格对象
     * @param params    文字替换map
     * @param picParams 图片替换map
     * @param indoc
     * @throws Exception
     */
    public static void replaceTable(IBodyElement para, Map<String, Object> params,
                                    Map<String, ImageData> picParams, XWPFDocument indoc)
            throws Exception {
        Matcher matcher;
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        table = (XWPFTable) para;
        rows = table.getRows();
        for (XWPFTableRow row : rows) {
            cells = row.getTableCells();
            int cellsize = cells.size();
            int cellcount = 0;
            for (cellcount = 0; cellcount < cellsize; cellcount++) {
                XWPFTableCell cell = cells.get(cellcount);
                String runtext = "";
                List<XWPFParagraph> ps = cell.getParagraphs();
                for (XWPFParagraph p : ps) {
                    for (XWPFRun run : p.getRuns()) {
                        runtext = run.text();
                        matcher = matcher(runtext);
                        if (matcher.find()) {
                            if (picParams != null) {
                                for (String pickey : picParams.keySet()) {
                                    if (matcher.group().equals(pickey)) {
                                        run.setText("", 0);
                                        replacePic(run, picParams.get(pickey), indoc);
                                    }
                                }
                            }
                            if (params != null) {
                                for (String pickey : params.keySet()) {
                                    if (matcher.group().equals(pickey)) {
                                        run.setText(params.get(pickey) + "", 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
