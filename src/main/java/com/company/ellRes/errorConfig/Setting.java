package com.company.ellRes.errorConfig;


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Setting {
    public Setting() {
    }

    public Properties properties() {
        InputStreamReader w = null;
        Properties proper = new Properties();

        try {
            FileInputStream k = new FileInputStream("C:\\DocumentLift\\errorConfig\\error.ini");
            w = new InputStreamReader(k,"UTF-8" );
            proper.load(w);
            w.close();
            return proper;
        } catch (FileNotFoundException var4) {
            System.out.println(var4.getMessage());
            return null;
        } catch (IOException var5) {
            System.out.println(var5.getMessage());
            return null;
        }
    }
}
