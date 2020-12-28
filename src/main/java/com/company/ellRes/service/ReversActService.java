package com.company.ellRes.service;

import com.company.ellRes.repository.ReversActRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReversActService {

    @Autowired
    private ReversActRepo reversActRepo;
}
