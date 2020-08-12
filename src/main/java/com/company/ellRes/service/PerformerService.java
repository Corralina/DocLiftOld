package com.company.ellRes.service;

import com.company.ellRes.domian.Performer;
import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.User;
import com.company.ellRes.repository.PerformerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PerformerService {

    @Autowired
    PerformerRepo performerRepo;

    public void save(Performer performer){
        performerRepo.save(performer);
    }

    public Iterable<Performer> allUser(User user){
        return performerRepo.findTop50ByUserAndResolutionStatusFinishTrue(user);
    }

    public Iterable<Performer> byResolution(Resolution resolution){
        return performerRepo.findTop50ByResolution(resolution);
    }

    public void drop(Performer performer){performerRepo.delete(performer);}

    public Iterable<Performer> filter(User user, String number, String agrees, String filling){
        return performerRepo.findAllByUserAndResolutionStatusFinishTrueAndResolutionDocumentNumberLikeAndResolutionAgreesInformationIndividualInitialsLikeAndResolutionFillingInformationIndividualInitialsLike(user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%");
    }

    public Iterable<Performer> filterData(LocalDate start, LocalDate stop, User user, String number, String agrees, String filling){
        return performerRepo.findAllByResolutionVisaDataAfterAndResolutionVisaDataBeforeAndUserAndResolutionStatusFinishTrueAndResolutionDocumentNumberLikeAndResolutionAgreesInformationIndividualInitialsLikeAndResolutionFillingInformationIndividualInitialsLike(start, stop, user, "%" + number + "%", "%" + agrees + "%", "%" + filling + "%");
    }

    public Iterable<Performer> filterOneDate(LocalDate date, User user, String number, String agrees, String filling){
        return performerRepo.findAllByResolutionVisaDataAndUserAndResolutionStatusFinishTrueAndResolutionDocumentNumberLikeAndResolutionAgreesInformationIndividualInitialsLikeAndResolutionFillingInformationIndividualInitialsLike(date, user, number, agrees, filling);
    }
}
