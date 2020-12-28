package com.company.ellRes.service;

import com.company.ellRes.domian.Act;
import com.company.ellRes.domian.DocumentAct;
import com.company.ellRes.domian.User;
import com.company.ellRes.repository.ActRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActService {

    @Autowired
    private ActRepo actRepo;

    public Act byFile (DocumentAct documentAct){return actRepo.findFirstByDocumentAct(documentAct);}


    public void save(Act act){actRepo.save(act);}

    public Iterable<Act> list(User user){
        return actRepo.findTop50ByAuthor(user, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Act> listt(User user, ArrayList list){
        return actRepo.findTop50ByAuthorOrIdIn(user, list,  Sort.by(Sort.Direction.DESC, "date"));
    }
}
