package com.account.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class EncryptUtil {
    public static final String encryptMD5(String source){
        if(source==null){
            source="";
        }
        Md5Hash md5Hash = new Md5Hash(source);
        return md5Hash.toString();
    }
}
