
public class RotX {

    // Lowercase letters with accents, ñ, ç
    private static final char[] LOWER = {
        'a','b','c','d','e','f','g','h','i','j','k','l','m',
        'n','ñ','o','p','q','r','s','t','u','v','w','x','y','z',
        'à','á','è','é','ì','í','ò','ó','ù','ú','ü','ÿ','ç'
    };

    // Uppercase letters with accents, Ñ, Ç
    private static final char[] UPPER = {
        'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z',
        'À','Á','È','É','Ì','Í','Ò','Ó','Ù','Ú','Ü','Ÿ','Ç'
    };

    // helper: shift one character inside the alphabet
    private static char shiftChar(char c, char[] array, int shift) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                int newIndex = (i + shift) % array.length;
                if (newIndex < 0) newIndex += array.length; // handle negative shift
                return array[newIndex];
            }
        }
        return c; // if it's not in the alphabet, keep it the same
    }

        // Encrypt text with shift (xifra = encrypt)
    public static String xifraRotX(String text, int desplaçament) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLowerCase(c)) {
                result.append(shiftChar(c, LOWER, desplaçament));
            } else if (Character.isUpperCase(c)) {
                result.append(shiftChar(c, UPPER, desplaçament));
            } else {
                result.append(c); // keep spaces and punctuation
            }
        }

        return result.toString();
    }

        // Decrypt text with shift (desxifra = decrypt)
    public static String desxifraRotX(String text, int desplaçament) {
        // just call xifraRotX but with the negative shift
        return xifraRotX(text, -desplaçament);
    }

        /**
     * Try all possible shifts and print the decrypted result for each.
     * Prints from shift 0 .. (ALPHABET_SIZE - 1).
     */
    public static void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("Brute-force table (shift 0 .. " + (LOWER.length - 1) + "):");
        for (int shift = 0; shift < LOWER.length; shift++) {
            String attempt = desxifraRotX(cadenaXifrada, shift);
            System.out.printf("(%d)->%s%n", shift, attempt);
        }
    }




    public static void main(String[] args) {
        System.out.println(xifraRotX("Hola, Mr. calçot", 4));
        
        String encrypted = xifraRotX("Perdó, per tu què és?", 6); // example: create an encrypted string
    System.out.println("Encrypted message: " + encrypted);
    System.out.println("----------------------------");
    forcaBrutaRotX(encrypted);

    }
}
