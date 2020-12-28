package com.company.ellRes.repository;

import com.company.ellRes.domian.Act;
import com.company.ellRes.domian.TimingAct;
import com.company.ellRes.domian.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimingActRepo extends JpaRepository<TimingAct, Long> {

    Iterable<TimingAct> findTop1000ByUserOrActAuthor(User user, User actAuthor, Sort sort);

    Iterable<TimingAct> findAllByActOrderByStatusAsc(Act act);

}
