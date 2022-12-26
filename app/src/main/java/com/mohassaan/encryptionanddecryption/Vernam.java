package com.mohassaan.encryptionanddecryption;

public class Vernam {

    String encrypt(String text,String key)
    {
        String actualKey=generateKey(text.length(),key);
        String result="";

        for (int i = 0; i <text.length() ; i++) {
            result+=(text.charAt(i)^actualKey.charAt(i));
        }
        return result;
    }
    String decrypt(String text,String key)
    {
        String actualKey=generateKey(text.length(),key);
        String result="";

        for (int i = 0; i <text.length() ; i++) {
            result+=(text.charAt(i)^actualKey.charAt(i));
        }
        return result;
    }
    private String generateKey(int textLength,String key){
        String result="";
        for (int i = 0; i <textLength ; i++) {
            result+=key.charAt(i % key.length());
        }
        return result;
    }
}
