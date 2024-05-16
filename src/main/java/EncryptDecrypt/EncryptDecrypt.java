package EncryptDecrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class EncryptDecrypt {

    private static String KEY = "password";
    private static final String IV = "12345678"; // 8 bytes IV



    public static byte[] encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        KeySpec keySpec = new DESKeySpec(KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey,iv);

        byte[] paddedPlainText = padString(plainText);
        return cipher.doFinal(paddedPlainText);
    }
    public static String decrypt(byte[] cipherText) throws Exception {
    try {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        KeySpec keySpec = new DESKeySpec(KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decryptedBytes = cipher.doFinal(cipherText);

        int paddingLength = decryptedBytes[decryptedBytes.length - 1];
        byte[] unpaddedBytes = Arrays.copyOfRange(decryptedBytes, 0, decryptedBytes.length - paddingLength);

        return new String(unpaddedBytes);
    }catch (Exception e){
        e.printStackTrace();
        return null;
    }
}

    public  static byte[] padString(String text) {
        int padding = 8 - (text.length() % 8);
        StringBuilder stringBuilder = new StringBuilder(text);
        for (int i = 0; i < padding; i++) {
            stringBuilder.append((char) padding);
        }
        return stringBuilder.toString().getBytes();
    }
}
