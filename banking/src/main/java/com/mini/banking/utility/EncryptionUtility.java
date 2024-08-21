package com.mini.banking.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtility {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String plainText) {
        return passwordEncoder.encode(plainText);
    }

    public static boolean matches(String plainText, String encryptedText) {
        return passwordEncoder.matches(plainText, encryptedText);
    }

}
