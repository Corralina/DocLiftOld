package com.company.ellRes.service;


import com.company.ellRes.domian.DocumentAct;
import com.company.ellRes.domian.User;
import com.company.ellRes.repository.DocumentActRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentActService {
    @Autowired
    private DocumentActRepo documentActRepo;

    public void save(DocumentAct documentAct) {
        documentActRepo.save(documentAct);
    }

    public Iterable<DocumentAct> list(User user){
        return documentActRepo.findTop50ByAuthor(user, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Optional<DocumentAct> doc(Long id){return documentActRepo.findById(id);}

    public Iterable<DocumentAct> listActFalse(User user){
        return documentActRepo.findTop50ByAuthorAndActFalse(user, Sort.by(Sort.Direction.DESC, "date"));
    }
}
