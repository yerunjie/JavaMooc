package com.ecnu.admission.persistence;

import com.ecnu.admission.persistence.impl.mybatis.MyBatisAdmissionRepositoryImpl;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
public class AdmissionRepositoryFactory {

    public static IAdmissionRepository getRepository() {
        return new MyBatisAdmissionRepositoryImpl();
    }
}
