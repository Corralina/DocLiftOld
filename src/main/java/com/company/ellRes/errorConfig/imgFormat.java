package com.company.ellRes.errorConfig;

public class imgFormat {

    public int image(String str){
        if (str.indexOf(".jpg") < 0 && str.indexOf(".png") < 0){
            return 107;
        }
        return 0;
    }
}
