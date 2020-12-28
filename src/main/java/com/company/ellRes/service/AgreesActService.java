package com.company.ellRes.service;

import com.company.ellRes.domian.AgreesAct;
import com.company.ellRes.repository.AgreesActRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgreesActService {

    @Autowired
    private AgreesActRepo agreesActRepo;

    public AgreesAct save(AgreesAct agreesAct){
        return agreesActRepo.save(agreesAct);
    }


}
