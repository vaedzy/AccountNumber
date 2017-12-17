package com.account.utils;

import java.security.MessageDigest;

public class CipherUtil {
    private final static String[] hexDigits={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
    public static String generateName(String inputString){
        return encodeByMD5(inputString);
    }
    public static boolean validateName(String name,String inputString){
        if (name.equals(encodeByMD5(inputString))){
            return true;
        }else{
            return false;
        }
    }
    private static String encodeByMD5(String originString){
        if (originString!=null){
            try{
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] results = messageDigest.digest(originString.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    private static String byteArrayToHexString(byte[] b){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<b.length;i++){
              stringBuffer.append(byteToHexString(b[i]));
        }
        return stringBuffer.toString();
    }
    private static String byteToHexString(byte b){
        int n = b;
        if (n<0)
            n = 256+n;
            int d1 = n/16;
            int d2 = n%16;
            return hexDigits[d1]+hexDigits[d2];
    }
    public static void main(String[]args){
        String name = "13241997744";
        String name2 = "";
        CipherUtil cipherUtil = new CipherUtil();
        System.out.println(name);
        name2 = cipherUtil.generateName(name);
        System.out.println(name2);
        //加密
        //解密
        if(cipherUtil.validateName(name2,"13241997744")){
            System.out.println("匹配");
        }
    }
}
