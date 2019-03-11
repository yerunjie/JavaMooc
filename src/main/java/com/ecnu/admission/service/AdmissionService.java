package com.ecnu.admission.service;

import org.apache.poi.ss.usermodel.Workbook;

import javax.mail.internet.MimeMessage;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
public interface AdmissionService {

    int importFromExcel(Workbook workbook);

    int sendMail(MimeMessage mimeMessage);
}
