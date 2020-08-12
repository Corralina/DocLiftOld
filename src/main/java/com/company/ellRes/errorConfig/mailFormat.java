package com.company.ellRes.errorConfig;

public class mailFormat {

    public int format(String str){
        if (str.indexOf("@") < 0){
            return 105;
        }
        return 0;
    }
}
