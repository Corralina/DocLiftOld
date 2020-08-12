package com.company.ellRes.service;


import com.company.ellRes.domian.Static;
import com.company.ellRes.domian.Visa;
import com.company.ellRes.repository.StaticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaticService {

    @Autowired
    StaticRepo staticRepo;

    public void save(Static stat){
        staticRepo.save(stat);
    }

    public Iterable<Static> byVisa(Visa visa){
        return staticRepo.findAllByVisa(visa);
    }
}
