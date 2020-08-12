package com.company.ellRes.service;


import com.company.ellRes.domian.Individual;
import com.company.ellRes.repository.IndividualRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividualService {

    @Autowired
    private IndividualRepo individualRepo;

    public void save(Individual individual){
        individualRepo.save(individual);
    }
}
