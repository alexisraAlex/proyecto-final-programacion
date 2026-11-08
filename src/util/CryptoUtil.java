package util;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class CryptoUtil {
    private static final int NONCE_LENGTH = 12;
    private static final int TAG_LENGTH_BITS = 128;

    private static SecretKeySpec getKey() {
        String encodedKey = System.getenv("APP_ENCRYPTION_KEY");
        if (encodedKey == null || encodedKey.isBlank()) {
            throw new IllegalStateException("Set APP_ENCRYPTION_KEY to a Base64-encoded AES key.");
        }
        byte[] key = Base64.getDecoder().decode(encodedKey);
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalStateException("APP_ENCRYPTION_KEY must decode to 16, 24, or 32 bytes.");
        }
        return new SecretKeySpec(key, "AES");
    }

    public static String encriptar(String texto) throws Exception {
        byte[] nonce = new byte[NONCE_LENGTH];
        new SecureRandom().nextBytes(nonce);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), new GCMParameterSpec(TAG_LENGTH_BITS, nonce));
        byte[] encrypted = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
        byte[] result = new byte[nonce.length + encrypted.length];
        System.arraycopy(nonce, 0, result, 0, nonce.length);
        System.arraycopy(encrypted, 0, result, nonce.length, encrypted.length);
        return Base64.getEncoder().encodeToString(result);
    }

    public static String desencriptar(String textoEncriptado) throws Exception {
        byte[] input = Base64.getDecoder().decode(textoEncriptado);
        if (input.length <= NONCE_LENGTH) {
            throw new IllegalArgumentException("Invalid encrypted value.");
        }
        byte[] nonce = Arrays.copyOfRange(input, 0, NONCE_LENGTH);
        byte[] encrypted = Arrays.copyOfRange(input, NONCE_LENGTH, input.length);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, getKey(), new GCMParameterSpec(TAG_LENGTH_BITS, nonce));
        return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
    }
}