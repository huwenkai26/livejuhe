package com.jinhe.juhe.livejuhe.utils;

public class Illllllll {
    public static String Wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww(byte[] bArr, String str) {
        int length = bArr.length;
        int length2 = str.length();
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (bArr[i] ^ str.charAt(i % length2));
        }
        return new String(bArr);
    }
}