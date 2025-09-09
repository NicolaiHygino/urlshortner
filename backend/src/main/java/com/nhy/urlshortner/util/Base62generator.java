package com.nhy.urlshortner.util;


import java.security.SecureRandom;

public class Base62generator {
    private static final String BASE62_CHARS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandom(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length cannot be negative.");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(BASE62_CHARS.length());
            sb.append(BASE62_CHARS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
