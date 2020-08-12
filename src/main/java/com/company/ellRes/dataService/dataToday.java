package com.company.ellRes.dataService;

import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;

@Service
public class dataToday {

    public LocalDate dateToday() throws ParseException {
        LocalDate myObj = LocalDate.now();

        return myObj;
    }
}
