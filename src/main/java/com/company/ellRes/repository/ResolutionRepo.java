package com.company.ellRes.repository;

import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;

public interface ResolutionRepo extends JpaRepository<Resolution, Long>{

    Iterable<Resolution> findTop50ByAgrees(User user, Sort sort);


    Iterable<Resolution> findTop50ByFilling(User user, Sort sort);


    Iterable<Resolution> findTop50ByStatusFinishFalse(Sort sort);
    Iterable<Resolution> findTop50ByStatusFinishTrue(Sort sort);


    Iterable<Resolution> findTop50ByAgreesOrFilling(User user,User user2, Sort sort);




    Iterable<Resolution> findAllByDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike(String number, String agrees, String filling, Sort sort);
    Iterable<Resolution> findAllByDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore(String number, String agrees, String filling, LocalDate start, LocalDate stop, Sort sort);
    Iterable<Resolution> findAllByDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate(String number, String agrees, String filling, LocalDate date, Sort sort);

    Iterable<Resolution> findAllByAgreesOrFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike(User user,User user2, String number, String agrees, String filling, Sort sort);
    Iterable<Resolution> findAllByAgreesOrFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore(User user, User user2, String number, String agrees, String filling, LocalDate start, LocalDate stop, Sort sort);
    Iterable<Resolution> findAllByAgreesOrFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate(User user, User user2, String number, String agrees, String filling, LocalDate date, Sort sort);

    Iterable<Resolution> findAllByAgreesAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike(User user, String number, String agrees, String filling, Sort sort);
    Iterable<Resolution> findAllByAgreesAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore(User user, String number, String agrees, String filling, LocalDate start, LocalDate stop, Sort sort);
    Iterable<Resolution> findAllByAgreesAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate(User user, String number, String agrees, String filling, LocalDate date, Sort sort);

    Iterable<Resolution> findAllByFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLike(User user, String number, String agrees, String filling, Sort sort);
    Iterable<Resolution> findAllByFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDateAfterAndDateBefore(User user, String number, String agrees, String filling, LocalDate start, LocalDate stop, Sort sort);
    Iterable<Resolution> findAllByFillingAndDocumentNumberLikeAndAgreesInformationIndividualInitialsLikeAndFillingInformationIndividualInitialsLikeAndDate(User user, String number, String agrees, String filling, LocalDate date, Sort sort);


    Iterable<Resolution> findAllByDateAfter(LocalDate start);

}
