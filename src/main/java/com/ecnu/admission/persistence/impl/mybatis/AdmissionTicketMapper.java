package com.ecnu.admission.persistence.impl.mybatis;

import com.ecnu.admission.AdmissionTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by yerunjie on 2019-03-08
 *
 * @author yerunjie
 */
public interface AdmissionTicketMapper {

    @Insert("INSERT INTO `admission_ticket`(`name`, `candidate_number`, `id_number`, `gender`, `subject`, `address`, `time`, `seat`, `email`) " +
            "VALUES ( #{name}, #{candidateNumber}, #{idNumber}, #{gender}, #{subject}, #{address}, #{time}, #{seat}, #{email});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int createAdmissionTicket(AdmissionTicket admissionTicket);


    @Select("SELECT * FROM admission_ticket")
    List<AdmissionTicket> getAll();
}
