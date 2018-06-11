/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Shree
 */
public class BasicPasswordEncryptor implements PasswordEncoder {

    private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int SALT_SIZE = 8;

    private String hexStringFromBytes(byte[] b) {
        String hex = "";
        for (int i = 0; i < b.length; i++) {
            int msb = ((int) b[i] & 0x000000FF) / 16;
            int lsb = ((int) b[i] & 0x000000FF) % 16;
            hex = hex + hexChars[msb] + hexChars[lsb];
        }
        return hex;
    }

    public static byte[] hexStringToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public String encryptPassword(String userPassword) {
        try {
            SaltGenerator saltGenerator = new SaltGenerator();
            byte[] salt = saltGenerator.generateSalt(SALT_SIZE);
            byte[] data = userPassword.getBytes();
            return digest(data, salt);
        } catch (NoSuchAlgorithmException nsaex) {
            return null;
        }
    }

    private String digest(final byte[] data, final byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(salt);
        md5.update(data);
        byte[] hash = md5.digest();
        byte[] digest = new byte[salt.length + hash.length];
        System.arraycopy(salt, 0, digest, 0, salt.length);
        System.arraycopy(hash, 0, digest, salt.length, hash.length);
        return hexStringFromBytes(digest);
    }

    public boolean checkPassword(String inputPassword, String encryptedPassword) {
        boolean matches = false;
        try {
            byte[] salt = new byte[SALT_SIZE];
            byte[] data = hexStringToBytes(encryptedPassword);
            System.arraycopy(data, 0, salt, 0, SALT_SIZE);
            String expectedPassword = digest(inputPassword.getBytes(), salt);
            matches = expectedPassword.equals(encryptedPassword);
        } catch (NoSuchAlgorithmException nsaex) {
        }
        return matches;
    }

    public static void main(String[] args) {
        try {
            BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
            String encryptedPassword = encryptor.encryptPassword("test1234");
            System.out.println("Encrypted password:	" + encryptedPassword);
            System.out.println("Matches:" + encryptor.checkPassword("test1234", "4574d61815e6565e4e1203929f67fc2fc3d2fb03b292c6ff"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encryptPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return checkPassword(rawPassword.toString(), encodedPassword);
    }

}
