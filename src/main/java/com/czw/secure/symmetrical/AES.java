package com.czw.secure.symmetrical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author ZeviChen , 2017/2/7 18:52
 */
public class AES {
    
    private static Logger log = LoggerFactory.getLogger(AES.class);
    private static KeyGenerator kgen;
    private static Cipher cipher;
    
    static{
        try {
            kgen = KeyGenerator.getInstance("AES");
            cipher = Cipher.getInstance("AES");// 创建密码器
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(),e);
        } catch (NoSuchPaddingException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static byte[] encrypt(byte[] content, String password) {
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            
            try{
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
                return cipher.doFinal(content);
            } catch (BadPaddingException e) {
                log.error(e.getMessage(),e);
            } catch (IllegalBlockSizeException e) {
                log.error(e.getMessage(),e);
            } catch (InvalidKeyException e) {
                log.error(e.getMessage(),e);
            }
        return null;
    }

    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(),e);
        } catch (InvalidKeyException e) {
            log.error(e.getMessage(),e);
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(),e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static String encrypt(String data,String password){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            return base64Encoder.encode(encrypt(data.getBytes("UTF-8"),password));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static String decrypt(String data,String password){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return new String(decrypt(base64Decoder.decodeBuffer(data),password));
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }
    
    public static void main(String[] args){
        String data = "hello world";
        String password = "12345678";

        String encode = encrypt(data,password);
        System.out.println("加密数据："+encode);
        System.out.println("解密数据："+decrypt(encode,password));

        
    }
}
