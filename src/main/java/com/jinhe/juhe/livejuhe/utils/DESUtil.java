package com.jinhe.juhe.livejuhe.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class DESUtil {
    private static final String DES = "DES";

    public static String encrypt(String str, String str2) throws Exception {
        return Base64.getEncoder().encodeToString(encrypt(str.getBytes(), str2.getBytes()));
    }

    public static String decrypt(String str, String str2) throws Exception {
        if (str == null) {
            return null;
        }
        return new String(decrypt(Base64.getDecoder().decode(str), str2.getBytes()));
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        Key generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher instance = Cipher.getInstance("DES/ECB/PKCS5Padding");
        instance.init(1, generateSecret);
        return instance.doFinal(bArr);
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        Key generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher instance = Cipher.getInstance("DES/ECB/PKCS5Padding");
        instance.init(2, generateSecret);
        return instance.doFinal(bArr);
    }
}