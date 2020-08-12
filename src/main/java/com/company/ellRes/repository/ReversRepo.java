package com.company.ellRes.repository;

import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.Revers;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReversRepo extends JpaRepository<Revers, Long> {

    Revers findFirstByResolutionAndFinishFalse(Resolution resolution);

    Iterable<Revers> findAllByResolution(Resolution resolution, Sort sort);
}
