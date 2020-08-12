package com.company.ellRes.errorConfig;

public class errorController {

    public int parse(String str,int[] turn){
        correchness correchness = new correchness();
        nullConfig nullConfig = new nullConfig();
        mailFormat mailFormat = new mailFormat();
        phoneFormat phoneFormat = new phoneFormat();
        imgFormat imgFormat = new imgFormat();
        fileFormat fileFormat = new fileFormat();

        int v = 0;
        for (int i: turn) {
            if (i == 1){
                v = correchness.correct(str);
            }else if (i == 2){
                v = nullConfig.nullFiled(str);
            }else if (i == 3){
                v = mailFormat.format(str);
            }else if (i == 4){

            }else if (i == 5){
                v = imgFormat.image(str);
            }else if (i == 6){
                v = fileFormat.file(str);
            }
            if (v != 0){
                return v;
            }
        }
        return 0;
    }
}
