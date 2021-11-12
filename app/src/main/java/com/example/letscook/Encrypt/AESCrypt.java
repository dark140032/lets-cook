package com.example.letscook.Encrypt;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypt {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";

    public static String encrypt(String value)
    {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            String encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
            return encryptedValue64;
        }catch (Exception ex){

        }
        return null;
    }

    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(AESCrypt.KEY.getBytes(),AESCrypt.ALGORITHM);
        return key;
    }

}
