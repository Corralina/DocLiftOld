package com.company.ellRes.repository;

import com.company.ellRes.domian.Send;
import com.company.ellRes.domian.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SendRepo extends JpaRepository<Send, Long> {



}
