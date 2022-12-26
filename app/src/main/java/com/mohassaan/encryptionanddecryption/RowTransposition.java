package com.mohassaan.encryptionanddecryption;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class RowTransposition {
    private int actualKey;
    private ArrayList<Integer>arrayList=new ArrayList<Integer>();
    char [][] matrix;
    int numberOfLetters;
    void buildMatrix(String key,String text){

        actualKey=Integer.parseInt(key);
        numberOfLetters=text.length()/key.length();
        matrix=new char[numberOfLetters+2][key.length()];

        // fill the first row with key
        for (int i = 0; i <key.length(); i++) {
            matrix[0][i]=key.charAt(i);
        }

        // fill the matrix with text
        for (int i = key.length(); i <(numberOfLetters+2)*key.length(); i++) {
            int row = i / key.length();
            int col = i % key.length();

            if(i-key.length()<text.length())matrix[row][col]=text.charAt(i-key.length());
            else matrix[row][col]='*';
        }

}
void clear(String key)
{
    for (int i = 0; i <(numberOfLetters+2)*key.length(); i++) {
        int row = i / key.length();
        int col = i % key.length();

        matrix[row][col]=' ';
    }
}
 void convertKeyToArray()
 {
     while(actualKey>0){
         arrayList.add(actualKey%10);
         actualKey/=10;
     }
     Collections.sort(arrayList);
 }

 String encrypt(String key,String text){
        buildMatrix(key,text);
        convertKeyToArray();
        String result="";
     for (int i = 0; i <arrayList.size() ; i++) {

         int targetCol=getTragetCol(i,key.length());

         for(int j=1;j<numberOfLetters+2;j++){
             result+=matrix[j][targetCol];
         }
     }
     arrayList.clear();
     return result;
 }

 String decrypt(String key,String text){
        convertKeyToArray();
        String result="";
        for (int i = key.length(); i <(numberOfLetters+2)*key.length(); i++) {
            int row = i / key.length();
            int col = i % key.length();

            result+=matrix[row][col];
        }
        return result;
 }

   int getTragetCol(int arrylistIndex,int matrixWidth){
       int targetCol=-1;
       for (int i = 0; i <matrixWidth; i++) {
           if(Integer.parseInt(String.valueOf(matrix[0][i]))==arrayList.get(arrylistIndex)){
               targetCol=i;
               break;
           }
       }
       return targetCol;
    }

}
