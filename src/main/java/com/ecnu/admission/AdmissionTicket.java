package com.ecnu.admission;

import lombok.Data;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
@Data
public class AdmissionTicket {
    private int id;
    private String name;
    private String candidateNumber;
    private String idNumber;
    private String gender;
    private String subject;
    private String address;
    private String time;
    private String seat;
    private String email;
}
