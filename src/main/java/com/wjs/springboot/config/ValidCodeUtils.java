package com.wjs.springboot.config;

import java.util.Random;
import java.util.Stack;

/**
 * @author Wjs
 */
public class ValidCodeUtils {
    public static Integer generateValidCode(int length) {
        Integer code = null;
        if (length == 4) {
            code = new Random().nextInt(9999);
            if (code < 1000) {
                code = code + 1000;
            }
        } else if (length == 6) {
            code = new Random().nextInt(999999);
            if (code < 100000) {
                code = code + 100000;
            }
        } else {
            throw new RuntimeException("只能生成四位或六位验证码");
        }
        return code;
    }
    public static String generateValidateCode4String(int length){
        Random random=new Random();
        String hash1=Integer.toHexString(random.nextInt());
        return hash1.substring(0,length);
    }

}
