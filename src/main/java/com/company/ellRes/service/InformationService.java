package com.company.ellRes.service;


import com.company.ellRes.domian.Information;
import com.company.ellRes.repository.InformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    @Autowired
    private InformationRepo informationRepo;

    public void save(Information information){
        informationRepo.save(information);
    }
}
