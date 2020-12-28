package com.company.ellRes.service;


import com.company.ellRes.domian.StatusAct;
import com.company.ellRes.repository.StatusActRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusActService {
    @Autowired
    StatusActRepo statusActRepo;

    public StatusAct save(StatusAct statusAct){
        return statusActRepo.save(statusAct);
    }
}
