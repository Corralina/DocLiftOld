package com.company.ellRes.repository;

import com.company.ellRes.domian.Performer;
import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PerformerRepo extends JpaRepository<Performer, Long> {

    Iterable<Performer> findTop50ByUserAndResolutionStatusFinishTrue(User user);

    Iterable<Performer> findTop50ByResolution(Resolution resolution);

    Iterable<Performer> findAllByUserAndResolutionStatusFinishTrueAndResolutionDocumentNumberLikeAndResolutionAgreesInformationIndividualInitialsLikeAndResolutionFillingInformationIndividualInitialsLike(User user,String number, String agrees, String filling);

    Iterable<Performer> findAllByResolutionVisaDataAfterAndResolutionVisaDataBeforeAndUserAndResolutionStatusFinishTrueAndResolutionDocumentNumberLikeAndResolutionAgreesInformationIndividualInitialsLikeAndResolutionFillingInformationIndividualInitialsLike(LocalDate start, LocalDate stop, User user, String number, String agrees, String filling);

    Iterable<Performer> findAllByResolutionVisaDataAndUserAndResolutionStatusFinishTrueAndResolutionDocumentNumberLikeAndResolutionAgreesInformationIndividualInitialsLikeAndResolutionFillingInformationIndividualInitialsLike(LocalDate date, User user, String number, String agrees, String filling);



}
