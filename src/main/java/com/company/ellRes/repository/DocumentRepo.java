package com.company.ellRes.repository;

import com.company.ellRes.domian.Document;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DocumentRepo extends JpaRepository<Document, Long> {

    Iterable<Document> findTop50ByNumberNotNull(Sort sort);

    Iterable<Document> findAllByNumberLikeAndAuthorInformationIndividualInitialsLike(String number, String author, Sort sort);

    Iterable<Document> findAllByNumberLikeAndAuthorInformationIndividualInitialsLikeAndDateAfterAndDateBefore(String number, String author, LocalDate start, LocalDate stop, Sort sort);

    Iterable<Document> findAllByNumberLikeAndAuthorInformationIndividualInitialsLikeAndDate(String number, String author, LocalDate date, Sort sort);





    Iterable<Document> findTop50ByResolutionFalse(Sort sort);

    Iterable<Document> findAllByResolutionFalseAndNumberLikeAndAuthorInformationIndividualInitialsLike(String number, String author, Sort sort);

    Iterable<Document> findAllByResolutionFalseAndNumberLikeAndAuthorInformationIndividualInitialsLikeAndDateAfterAndDateBefore(String number, String author, LocalDate start, LocalDate stop, Sort sort);

    Iterable<Document> findAllByResolutionFalseAndNumberLikeAndAuthorInformationIndividualInitialsLikeAndDate(String number, String author, LocalDate date, Sort sort);


}
