package com.company.ellRes.service;


import com.company.ellRes.domian.Contact;
import com.company.ellRes.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    public void save(Contact contact){
        contactRepo.save(contact);
    }
}
