package br.unicamp.training.movieapp.utils;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SecurityUtils {

    private SecretKey mSecretKey;

    public SecurityUtils() {
        try {
            DESKeySpec keySpec = new DESKeySpec(Constants.API_KEY.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            mSecretKey = keyFactory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException
                | InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public String encode(String value) {
        try {
            byte[] valueByte = value.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, mSecretKey);
            String encrypted = Base64.encodeToString(cipher.doFinal(valueByte), Base64.DEFAULT);
            return encrypted;
        } catch (UnsupportedEncodingException | NoSuchPaddingException
                | NoSuchAlgorithmException | BadPaddingException
                | InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decode(String encryptedValue) {
        byte[] encryptedBytes = Base64.decode(encryptedValue, Base64.DEFAULT);

        try {
            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(Cipher.DECRYPT_MODE, mSecretKey);
            byte[] plainTextPwdBytes = cipher.doFinal(encryptedBytes);
            String result = new String(plainTextPwdBytes, "UTF-8");

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

}
