import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;



public class AES{

    public static final String ALGORITHM_XIFRAT = "AES";
    public static final String ALGORITHM_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16; 
    private static final byte[] iv = new byte[MIDA_IV];    
    private static final String CLAU = "hastalavistaK"; 

    public static byte[] xifraAES(String message, String password) throws Exception {
        byte[] msgBytes = message.getBytes("UTF-8");

        // Generate a random IV (16 bytes)
        new Random().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Generate a hash from the password (SHA-256)
        MessageDigest sha = MessageDigest.getInstance(ALGORITHM_HASH);
        byte[] keyBytes = sha.digest(password.getBytes("UTF-8"));
        keyBytes = Arrays.copyOf(keyBytes, 16); // use first 16 bytes → AES-128
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM_XIFRAT);

        // Initialize cipher for encryption
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] encrypted = cipher.doFinal(msgBytes);

        // Combine IV + encrypted data
        byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
        System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length);

        // Return full encrypted message
        return ivAndEncrypted;
    }

    public static byte[] desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        // Extract IV (first 16 bytes)
        byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extract encrypted part (after IV)
        byte[] encrypted = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);

        // Hash the password again to get the same key
        MessageDigest sha = MessageDigest.getInstance(ALGORITHM_HASH);
        byte[] keyBytes = sha.digest(password.getBytes("UTF-8"));
        keyBytes = Arrays.copyOf(keyBytes, 16);
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM_XIFRAT);

        // Initialize cipher for decryption
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        // Decrypt and return plaintext bytes
        return cipher.doFinal(encrypted);
    }

    public static void main(String[] args) throws Exception {
        
        String msgs[] = {"Hola, com estàs?", "Lorem ipsum", "Ágora ìlla Ott."};

        for (String msg : msgs) {
            byte[] bXifrat = null;
            String desxifrat = "";

            try {
                bXifrat = xifraAES(msg, CLAU);
                byte[] bDesxifrat = desxifraAES(bXifrat, CLAU);
                desxifrat = new String(bDesxifrat, "UTF-8");
            } catch (Exception e) {
                System.err.println("Error en xifrat/desxifrat: " + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Missatge original: " + msg);
            System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(bXifrat));
            System.out.println("Descrypted: " + desxifrat);
        }

    }
}
