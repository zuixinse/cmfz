package com.baizhi.util;

import java.util.Random;

public class CodeUtil {
    public static String getCode(int n){
        String code="";
        for(int i=0;i<n;i++){
            code+=new Random().nextInt(10);
        }
        return code;
    }
}
