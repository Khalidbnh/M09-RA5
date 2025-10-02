package src;

public class Rot13 {
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

    // Cipher method with shift
    public static String cipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLowerCase(c)) {
                result.append(shiftChar(c, LOWER, shift));
            } else if (Character.isUpperCase(c)) {
                result.append(shiftChar(c, UPPER, shift));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Shift a character in the array
    private static char shiftChar(char c, char[] array, int shift) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                int newIndex = (i + shift) % array.length;
                if (newIndex < 0) newIndex += array.length; // handle negative shift
                return array[newIndex];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int shift = 6;

        String text1 = "ABC";
        String text2 = "XYZ";
        String text3 = "Hola, Mr. cal\u00E7ot";
        String text4 = "Perd\u00F3, per tu qu\u00E8 \u00E9s?";

        System.out.println("Xifrat");
        System.out.println("---------");
        System.out.println(text1 + " => " + cipher(text1, shift));
        System.out.println(text2 + " => " + cipher(text2, shift));
        System.out.println(text3 + " => " + cipher(text3, shift));
        System.out.println(text4 + " => " + cipher(text4, shift));

        System.out.println("\nDesxifrat");
        System.out.println("---------");
        System.out.println(cipher(text1, shift) + " => " + cipher(cipher(text1, shift), -shift));
        System.out.println(cipher(text2, shift) + " => " + cipher(cipher(text2, shift), -shift));
        System.out.println(cipher(text3, shift) + " => " + cipher(cipher(text3, shift), -shift));
        System.out.println(cipher(text4, shift) + " => " + cipher(cipher(text4, shift), -shift));
    }
}
