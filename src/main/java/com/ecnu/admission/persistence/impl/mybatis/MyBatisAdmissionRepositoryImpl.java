package com.ecnu.admission.persistence.impl.mybatis;

import com.ecnu.admission.AdmissionTicket;
import com.ecnu.admission.persistence.IAdmissionRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
public class MyBatisAdmissionRepositoryImpl implements IAdmissionRepository {

    private static SqlSession session;

    private static SqlSession getSession() {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载驱动！");
        } catch (ClassNotFoundException e1) {
            System.out.println("找不到驱动!");
            e1.printStackTrace();
        }
        if (session == null) {
            String resource = "mybatis-config.xml";  //告诉MyBatis核心配置文件在哪里
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession(true);
        }
        return session;
    }

    private AdmissionTicketMapper admissionTicketMapper;

    public MyBatisAdmissionRepositoryImpl() {
        admissionTicketMapper = getSession().getMapper(AdmissionTicketMapper.class);
    }

    @Override
    public List<AdmissionTicket> getAll() {
        return admissionTicketMapper.getAll();
    }

    @Override
    public int batchAdd(List<AdmissionTicket> admissionTickets) {
        for (AdmissionTicket admissionTicket : admissionTickets) {
            admissionTicketMapper.createAdmissionTicket(admissionTicket);
        }
        return 0;
    }
}
