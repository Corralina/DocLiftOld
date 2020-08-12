package com.company.ellRes.errorConfig;

public class errorString {

    public int parse(String str){

        if (str.indexOf("\"") > 0){
            return 101;
        }else if (str.indexOf("\\") > 0){
            return 102;
        }else if (str.indexOf(":") > 0){
            return 103;
        }else if (str.equals("")){
            return 104;
        }
        return 0;
    }


}
