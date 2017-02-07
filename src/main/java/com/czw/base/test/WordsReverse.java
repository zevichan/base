package com.czw.base.test;

/**
 * 基于单词反转字符串
 *
 * @author ZeviChen , 2016/10/26 21:57
 */
public class WordsReverse {
    public static void main(String[] args){
        String s = "  Hello world   I will reverse you";
        char[] carr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int flag = 1;
        for(int i = 0;i<carr.length;i++){
            if(carr[i] == ' '){
                sb.insert(0,carr[i]);
                flag = 1;
            }else
                sb.insert(flag++,carr[i]);
        }
        System.out.println(s);
        System.out.println(sb.toString());

    }
}
