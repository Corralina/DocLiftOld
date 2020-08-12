package com.company.ellRes.service;


import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.Revers;
import com.company.ellRes.repository.ReversRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReversService {

    @Autowired
    ReversRepo reversRepo;

    public void save(Revers revers){
        reversRepo.save(revers);
    }

    public Revers byResolutionActiv(Resolution resolution){
        return reversRepo.findFirstByResolutionAndFinishFalse(resolution);
    }

    public Iterable<Revers> byResolution(Resolution resolution){
        return reversRepo.findAllByResolution(resolution, Sort.by(Sort.Direction.DESC, "date"));
    }
}
