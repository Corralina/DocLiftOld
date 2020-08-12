package com.company.ellRes.service;


import com.company.ellRes.domian.Visa;
import com.company.ellRes.repository.VisaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisaService {
    @Autowired
    VisaRepo visaRepo;

    public Visa save(Visa visa){
        return visaRepo.save(visa);
    }

}
