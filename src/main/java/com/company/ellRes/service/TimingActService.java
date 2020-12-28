package com.company.ellRes.service;

import com.company.ellRes.domian.Act;
import com.company.ellRes.domian.TimingAct;
import com.company.ellRes.domian.User;
import com.company.ellRes.repository.TimingActRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TimingActService {

    @Autowired
    private TimingActRepo timingActRepo;

    public void save(TimingAct timingAct){
        timingActRepo.save(timingAct);
    }

    public Iterable<TimingAct> list(User user){
        return timingActRepo.findTop1000ByUserOrActAuthor(user, user, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<TimingAct> findByAct(Act act){
        return timingActRepo.findAllByActOrderByStatusAsc(act);
    }
}
