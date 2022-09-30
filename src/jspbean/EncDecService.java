package jspbean;

import java.util.Base64;

public class EncDecService {
    public static String encrypt(String str) {
        String enStr = Base64.getEncoder().encodeToString(str.getBytes());
        return enStr;
    }

    public static String decrypt(String str) {
        byte[] byteStr = Base64.getDecoder().decode(str);
        return new String(byteStr);
    }
}
