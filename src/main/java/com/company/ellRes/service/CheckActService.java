package com.company.ellRes.service;

import com.company.ellRes.repository.CheckActRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckActService {

    @Autowired
    private CheckActRepo checkActRepo;
}
