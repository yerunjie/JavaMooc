package com.ecnu.admission.persistence;

import com.ecnu.admission.AdmissionTicket;

import java.util.List;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
public interface IAdmissionRepository {

    List<AdmissionTicket> getAll();

    int batchAdd(List<AdmissionTicket> admissionTickets);
}
