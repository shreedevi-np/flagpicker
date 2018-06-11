/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Shree
 */
public class SaltGenerator {

    public static final String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    private final SecureRandom random;

    public SaltGenerator() throws NoSuchAlgorithmException {
        this.random = SecureRandom.getInstance(DEFAULT_SECURE_RANDOM_ALGORITHM);
        this.random.setSeed(System.currentTimeMillis());
    }

    public byte[] generateSalt(final int lengthBytes) {
        final byte[] salt = new byte[lengthBytes];
        synchronized (this.random) {
            this.random.nextBytes(salt);
        }
        return salt;
    }
}
