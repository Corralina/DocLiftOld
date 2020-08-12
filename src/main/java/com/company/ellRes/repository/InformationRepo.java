package com.company.ellRes.repository;

import com.company.ellRes.domian.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepo extends JpaRepository<Information, Long> {
}
