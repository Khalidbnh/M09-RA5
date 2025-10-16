import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;



public class AES{

    public static final String ALGORITHM_XIFRAT = "AES";
    public static final String ALGORITHM_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16; // 16 bytes for AES
    private static final byte[] iv = new byte[MIDA_IV];    // Initialization vector
    private static final String CLAU = "hastalavistaK"; // 16 bytes key for AES-128

    public static byte[] xifraAES(String message, String password) throws Exception {
        SecretKey key = KeyGenerator.getInstance(ALGORITHM_XIFRAT).generateKey();
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bMsgXifrat = cipher.doFinal(message.getBytes());
        return bMsgXifrat;
    }

    public static byte[] desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        SecretKey key = KeyGenerator.getInstance(ALGORITHM_XIFRAT).generateKey();
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bMsgDesxifrat = cipher.doFinal(bMsgXifrat);
        return bMsgDesxifrat;
    }

    public static void main(String[] args) throws Exception {
        
        String msgs[] = {"Hola, com estàs?", "Lorem ipsum", "Ágora ìlla Ott."};
        
        for(int i=0; i<msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrat = null;
            String desxifrat = "";

            try{
                bXifrat = xifraAES(msg, CLAU);
                desxifrat = new String(desxifraAES(bXifrat, CLAU));

            } catch(Exception e){
                System.err.println("Error en xifrat/desxifrat: " + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Missatge original: " + msg);
            System.out.println("Encrypted: " + new String(bXifrat));
            System.out.println("Descrypted: " + desxifrat);
        }

        
    }
}
