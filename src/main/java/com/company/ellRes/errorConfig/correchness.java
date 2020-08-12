package com.company.ellRes.errorConfig;

public class correchness {

    public int correct(String str){
        if (str.indexOf("\"") > 0){
            return 101;
        }else if (str.indexOf("\\") > 0){
            return 102;
        }else if (str.indexOf(":") > 0){
            return 103;
        }
        return 0;
    }
}
