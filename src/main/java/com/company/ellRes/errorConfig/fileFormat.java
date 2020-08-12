package com.company.ellRes.errorConfig;

public class fileFormat {

    public int file(String str){
        if (str.indexOf(".pdf") < 0){
            return 108;
        }
        return 0;
    }
}
