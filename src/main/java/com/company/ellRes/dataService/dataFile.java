package com.company.ellRes.dataService;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Service
public class dataFile {
    public String geteTodayYer() {
        String thisDate = null;
        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();
        c.setTime(currentDate);
        Date vchera = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        thisDate = sdf.format(vchera);
        return thisDate;
    }
}
