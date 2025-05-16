package de.nonbi.api.utils;

public class Crypto {

    public static String encrypt(String input, String key) {
        return base64(xor(input, key));
    }

    public static String decrypt(String encrypted, String key) {
        String decoded = fromBase64(encrypted);
        return xor(decoded, key);
    }

    private static String xor(String input, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            char k = key.charAt(i % key.length());
            result.append((char) (c ^ k));
        }
        return result.toString();
    }

    private static String base64(String input) {
        return java.util.Base64.getEncoder().encodeToString(input.getBytes());
    }

    private static String fromBase64(String input) {
        return new String(java.util.Base64.getDecoder().decode(input));
    }
}

