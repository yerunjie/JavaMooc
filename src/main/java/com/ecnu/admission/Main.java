package com.ecnu.admission;

import com.ecnu.admission.persistence.AdmissionRepositoryFactory;
import com.ecnu.admission.persistence.IAdmissionRepository;
import com.ecnu.admission.service.AdmissionService;
import com.ecnu.admission.service.impl.AsposeDocxTemplateImpl;
import com.ecnu.admission.service.impl.POIDocxImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
public class Main {

    private AdmissionService admissionService;
    private IAdmissionRepository repository;
    private Session session;
    private Transport transport;

    public void init() throws Exception {
        Properties props = new Properties();
        //设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        //2、创建定义整个应用程序所需的环境信息的 Session 对象
        session = Session.getDefaultInstance(props);
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        //4、根据session对象获取邮件传输对象Transport
        transport = session.getTransport();
        //设置发件人的账户名和密码
        transport.connect("1102266271@qq.com", "iwtcyqtoyijlfghi");

        repository = AdmissionRepositoryFactory.getRepository();
        admissionService = new AsposeDocxTemplateImpl();
    }

    public void close() throws Exception {
        transport.close();
    }

    public static void main(String[] args) throws Exception {
        AdmissionTicket admissionTicket = new AdmissionTicket();
        admissionTicket.setName("陈良育");
        admissionTicket.setCandidateNumber("12334567890");
        admissionTicket.setGender("男");
        admissionTicket.setAddress("这是地址");
        admissionTicket.setSeat("01");
        admissionTicket.setSubject("Java核心技术(进阶)");
        admissionTicket.setTime("10:00-12:00");
        admissionTicket.setIdNumber("0987654321");
        admissionTicket.setEmail("lychen@sei.ecnu.edu.cn");

        AdmissionService admissionService = new POIDocxImpl();
        admissionService.getPDF(admissionTicket);
        /*Workbook workbook = WorkbookFactory.create(new File("/Users/yerunjie/Desktop/admission_ticket.xlsx"));
        Main main = new Main();
        main.init();
        main.importFromExcel(workbook);
        main.sendAll();
        main.close();*/
    }

    public void sendAll() throws Exception {
        for (AdmissionTicket admissionTicket : repository.getAll()) {
            Message msg = formatAdmissionTicket(admissionTicket);
            //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(msg, msg.getAllRecipients());
        }
    }

    private MimeMessage formatAdmissionTicket(AdmissionTicket admissionTicket) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("1102266271@qq.com"));
        message.setRecipients(Message.RecipientType.TO, admissionTicket.getEmail());
        message.setSubject("请查收准考证");
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("<p>请查收准考证</p>", "text/html;charset=UTF-8");
        MimeBodyPart attachment = new MimeBodyPart();
        byte[] data = admissionService.getPDF(admissionTicket);
        DataHandler dataHandler = new DataHandler(new ByteArrayDataSource(data, "application/pdf"));
        attachment.setDataHandler(dataHandler);
        // 设置附件的文件名（需要编码）
        attachment.setFileName(MimeUtility.encodeText("准考证.pdf"));
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(attachment);
        mm.setSubType("mixed");
        message.setContent(mm);
        message.saveChanges();
        message.setHeader("Content-Transfer-Encoding", "base64");
        return message;
    }

    private void importFromExcel(Workbook workbook) {
        List<AdmissionTicket> admissionTickets = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            AdmissionTicket admissionTicket = new AdmissionTicket();
            int colIndex = 0;
            Cell cell = row.getCell(colIndex++);
            admissionTicket.setName(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setCandidateNumber(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setIdNumber(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setGender(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setSubject(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setAddress(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setTime(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setSeat(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setEmail(getStringValue(cell));

            admissionTickets.add(admissionTicket);
        }
        repository.batchAdd(admissionTickets);
        //return admissionTickets;
    }

    private static String getStringValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }
}
