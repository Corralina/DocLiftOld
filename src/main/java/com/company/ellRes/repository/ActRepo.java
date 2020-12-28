package com.company.ellRes.repository;

import com.company.ellRes.domian.Act;
import com.company.ellRes.domian.DocumentAct;
import com.company.ellRes.domian.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ActRepo extends JpaRepository<Act, Long> {

    Act findFirstByDocumentAct(DocumentAct documentAct);

    Iterable<Act> findTop50ByAuthor(User autor, Sort sort);

    Iterable<Act> findTop50ByAuthorOrIdIn(User author, ArrayList listId, Sort sort);

    Iterable<Act> findTop50ByAuthorAndStatusFinishFalse(User author, Sort sort);
}
