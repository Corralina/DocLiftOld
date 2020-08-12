package com.company.ellRes.repository;


import com.company.ellRes.domian.Static;
import com.company.ellRes.domian.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface StaticRepo extends JpaRepository<Static, Long> {

    Iterable<Static> findAllByVisa(Visa visa);

}
