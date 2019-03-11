package com.ecnu.admission.service;

import com.ecnu.admission.AdmissionTicket;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
public interface AdmissionService {

    byte[] getPDF(AdmissionTicket admissionTicket);
}
