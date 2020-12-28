package com.company.ellRes.repository;

import com.company.ellRes.domian.DocumentAct;
import com.company.ellRes.domian.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentActRepo extends JpaRepository<DocumentAct, Long> {

    Iterable<DocumentAct> findTop50ByAuthor(User autor, Sort sort);

    Iterable<DocumentAct> findTop50ByAuthorAndActFalse(User autor, Sort sort);

    DocumentAct findFirstById(Long id);


}
