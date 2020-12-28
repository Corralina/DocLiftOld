package com.company.ellRes.service;


import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.User;
import com.company.ellRes.repository.ResolutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResolutionService {

    @Autowired
    ResolutionRepo resolutionRepo;

    public Resolution save(Resolution resolution){
        return resolutionRepo.save(resolution);
    }

    public Iterable<Resolution> all(){
        return resolutionRepo.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> allAgrees(User user){
        return resolutionRepo.findTop50ByAgrees(user, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> allFilling(User user){
        return resolutionRepo.findTop50ByFilling(user, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> allFinishFalse(){
        return resolutionRepo.findTop50ByStatusFinishFalse(Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> allFinishTrue(){
        return resolutionRepo.findTop50ByStatusFinishTrue(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> combo(User user){
        return resolutionRepo.findTop50ByAgreesOrFilling(user, user, Sort.by(Sort.Direction.DESC, "date"));
    }



    public Iterable<Resolution> allFinishFalseFilter(String number, String agrees, String filling){
        return resolutionRepo.findAllByStatusFinishFalseAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike("%" + number + "%", "%" + agrees + "%", "%" + filling + "%", Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> allFinishFalseFilterDate(String number, String agrees, String filling, LocalDate start, LocalDate stop){
        return resolutionRepo.findAllByStatusFinishFalseAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore("%" + number + "%", "%" + agrees + "%", "%" + filling + "%", start, stop, Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> allFinishFalseFilterOneDate(String number, String agrees, String filling, LocalDate date){
        return resolutionRepo.findAllByStatusFinishFalseAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate("%" + number + "%", "%" + agrees + "%", "%" + filling + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> allFinishTrueFilter(String number, String agrees, String filling){
        return resolutionRepo.findAllByStatusFinishTrueAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike("%" + number + "%", "%" + agrees + "%", "%" + filling + "%", Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> allFinishTrueFilterDate(String number, String agrees, String filling, LocalDate start, LocalDate stop){
        return resolutionRepo.findAllByStatusFinishTrueAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore("%" + number + "%", "%" + agrees + "%", "%" + filling + "%", start, stop, Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> allFinishTrueFilterOneDate(String number, String agrees, String filling, LocalDate date){
        return resolutionRepo.findAllByStatusFinishTrueAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate("%" + number + "%", "%" + agrees + "%", "%" + filling + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }


    public Iterable<Resolution> comboFilter(User user, String number, String agrees, String filling){
        return resolutionRepo.findAllByAgreesOrFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike(user, user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%", Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> comboFilterDate(User user, String number, String agrees, String filling, LocalDate start, LocalDate stop){
        return resolutionRepo.findAllByAgreesOrFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore(user, user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%", start, stop, Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> comboFilterOneDate(User user, String number, String agrees, String filling, LocalDate date){
        return resolutionRepo.findAllByAgreesOrFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate(user, user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }



    public Iterable<Resolution> agreesFilter(User user, String number, String agrees, String filling){
        return resolutionRepo.findAllByAgreesAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike(user,"%" + number + "%", "%" + agrees + "%", "%" + filling + "%", Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> agreesFilterDate(User user, String number, String agrees, String filling, LocalDate start, LocalDate stop){
        return resolutionRepo.findAllByAgreesAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore(user,"%" + number + "%", "%" + agrees + "%", "%" + filling + "%", start, stop, Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> agreesFilterOneDate(User user, String number, String agrees, String filling, LocalDate date){
        return resolutionRepo.findAllByAgreesAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate(user,"%" + number + "%", "%" + agrees + "%", "%" + filling + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }



    public Iterable<Resolution> fillingFilter(User user, String number, String agrees, String filling){
        return resolutionRepo.findAllByFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike(user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%", Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> fillingFilterDate(User user, String number, String agrees, String filling, LocalDate start, LocalDate stop){
        return resolutionRepo.findAllByFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore(user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%", start, stop, Sort.by(Sort.Direction.DESC, "date"));
    }
    public Iterable<Resolution> fillingFilterOneDate(User user, String number, String agrees, String filling, LocalDate date){
        return resolutionRepo.findAllByFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate(user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }




}
