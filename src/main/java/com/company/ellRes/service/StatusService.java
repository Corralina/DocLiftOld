package com.company.ellRes.service;


import com.company.ellRes.domian.Status;
import com.company.ellRes.repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;

    public Status save(Status status){
        return statusRepo.save(status);
    }
}
