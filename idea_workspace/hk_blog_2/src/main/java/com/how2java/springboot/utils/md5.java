package com.how2java.springboot.utils;

import org.apache.commons.codec.digest.DigestUtils;

//加密
public class md5 {
    //第一次加密（没加盐）
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }
    //加盐
    private static final String salt = "1a2b3c4d";

    //加盐的md5
    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //加盐的md5（盐是自己定义的）
    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //第一次使用md5加密（无盐）然后再次使用md5加密(自定义的盐)
    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
}
