package com.company.ellRes.service;


import com.company.ellRes.domian.Document;
import com.company.ellRes.domian.User;
import com.company.ellRes.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepo documentRepo;

    public void save(Document document){documentRepo.save(document);}

    public Iterable<Document> allFree(){
        return documentRepo.findTop50ByResolutionFalse(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> allFreeFilter(String number, String author){
        return documentRepo.findAllByNumberLikeAndAuthorInformationIndividualInitialsLike("%" + number + "%", "%" + author + "%", Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> allFreeFilterDate(String number, String author, LocalDate start, LocalDate stop){
        return documentRepo.findAllByNumberLikeAndAuthorInformationIndividualInitialsLikeAndDateAfterAndDateBefore("%" + number + "%", "%" + author + "%", start, stop, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> allFreeFilterOneDate(String number, String author, LocalDate date){
        return documentRepo.findAllByNumberLikeAndAuthorInformationIndividualInitialsLikeAndDate("%" + number + "%", "%" + author + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }





    public Iterable<Document> all(){
        return documentRepo.findTop50ByNumberNotNull(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> allFilter(String number, String author){
        return documentRepo.findAllByNumberLikeAndAuthorInformationIndividualInitialsLike("%" + number + "%", "%" + author + "%", Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> allFilterDate(String number, String author, LocalDate start, LocalDate stop){
        return documentRepo.findAllByNumberLikeAndAuthorInformationIndividualInitialsLikeAndDateAfterAndDateBefore("%" + number + "%", "%" + author + "%", start, stop, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> allFilterOneDate(String number, String author, LocalDate date){
        return documentRepo.findAllByNumberLikeAndAuthorInformationIndividualInitialsLikeAndDate("%" + number + "%", "%" + author + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }





}
