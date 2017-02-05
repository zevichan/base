package com.czw.secure.symmetrical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author ZeviChen , 2017/2/5 0005 下午 3:25
 */
public class DES {

    private static final Logger log = LoggerFactory.getLogger(DES.class);
    private static SecureRandom secureRandom = new SecureRandom();
    private static SecretKeyFactory keyFactory;
    private static Cipher cipher;
    static{
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error(e.getMessage());
        }
    }

    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            SecretKey securekey = keyFactory.generateSecret(desKey);
            cipher.init(Cipher.ENCRYPT_MODE, securekey, secureRandom);
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public static String encrypt(String data,String password){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(encrypt(data.getBytes(),password));
    }

    public static byte[] decrypt(byte[] src, String password) throws Exception {
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKey securekey = keyFactory.generateSecret(desKey);
        cipher.init(Cipher.DECRYPT_MODE, securekey, secureRandom);
        return cipher.doFinal(src);
    }

    public static String decrypt(String data,String password) throws Exception {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return new String(decrypt(base64Decoder.decodeBuffer(data),password));
    }


    public static void main(String args[]) throws Exception {
        String str = "hello world";
        String password = "12345678";

        System.out.println("加密后：" + encrypt(str,password));

        System.out.println("解密后："+ decrypt(encrypt(str,password),password));



    }


}
