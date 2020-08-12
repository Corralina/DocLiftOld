package com.company.ellRes.service;


import com.company.ellRes.domian.Send;
import com.company.ellRes.repository.SendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired
    SendRepo sendRepo;


    public Iterable<Send> who(){
        return  sendRepo.findAll();
    }

    public void save(Send send){
        sendRepo.save(send);
    }
}
