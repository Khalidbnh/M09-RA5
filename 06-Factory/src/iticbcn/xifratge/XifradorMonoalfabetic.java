package iticbcn.xifratge;


import java.util.Random;
import java.util.Arrays;

public class XifradorMonoalfabetic {

    private static final char[] ALPHABET = {
        'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z',
        'À','Á','È','É','Ì','Í','Ò','Ó','Ù','Ú','Ü','Ÿ','Ç'
    };

    
    private static char[] permutedAlphabet;

    
    public static char[] permutaAlfabet(char[] alfabet) {
        char[] copy = Arrays.copyOf(alfabet, alfabet.length);
        Random rand = new Random();

        for (int i = copy.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = copy[i];
            copy[i] = copy[j];
            copy[j] = temp;
        }
        return copy;
    }

    
    public static String xifraMonoAlfa(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char upperChar = Character.toUpperCase(c);
 
                int index = indexOf(upperChar, ALPHABET);
                if (index != -1) {
                    char newChar = permutedAlphabet[index];
                    
                    result.append(isUpper ? newChar : Character.toLowerCase(newChar));
                } else {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    
    public static String desxifraMonoAlfa(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char upperChar = Character.toUpperCase(c);

                int index = indexOf(upperChar, permutedAlphabet);
                if (index != -1) {
                    char originalChar = ALPHABET[index];
                    
                    result.append(isUpper ? originalChar : Character.toLowerCase(originalChar));
                } else {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    
    private static int indexOf(char c, char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    
    public static void main(String[] args) {
        // Generate permutation
        permutedAlphabet = permutaAlfabet(ALPHABET);

        System.out.println("Original alphabet: " + Arrays.toString(ALPHABET));
        System.out.println("Permuted alphabet: " + Arrays.toString(permutedAlphabet));

        // Test messages
        String text = "Hola, com estàs? Ñandú i Çebolla!";

        // Encrypt
        String encrypted = xifraMonoAlfa(text);
        System.out.println("Encrypted: " + encrypted);

        // Decrypt
        String decrypted = desxifraMonoAlfa(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
