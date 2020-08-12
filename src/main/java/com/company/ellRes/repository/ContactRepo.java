package com.company.ellRes.repository;

import com.company.ellRes.domian.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
