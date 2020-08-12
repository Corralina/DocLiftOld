package com.company.ellRes.errorConfig;


import java.io.IOException;
import java.util.Properties;

public class errorValue {

    public String value(int v){
        Setting setting = new Setting();
        Properties properties = null;
        properties = setting.properties();
        return properties.getProperty(String.valueOf(v));
    }
}
