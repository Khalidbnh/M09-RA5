import java.util.Random;

public class Polialfabetic {
     static String alfabet = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ";
    static Random random;
    static int secretkey = 12345; 
    static char[][] permutations;

    
    static void initRandom(int seed) {
        random = new Random(seed);
    }

    // Generate a random permutation of the alphabet
    static char[] permutaAlfabet() {
        char[] lletres = alfabet.toCharArray();
        for (int i = 0; i < lletres.length; i++) {
            int j = random.nextInt(lletres.length);
            
            char temp = lletres[i];
            lletres[i] = lletres[j];
            lletres[j] = temp;
        }
        return lletres;
    }

    
    public static String xifraPoliAlfa(String msg) {
        StringBuilder resultat = new StringBuilder();
        int numAlfabets = permutations.length;
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            boolean minuscula = Character.isLowerCase(c);
            char base = Character.toUpperCase(c);

            int index = alfabet.indexOf(base);
            if (index != -1) { 
                char substitut = permutations[i % numAlfabets][index];
                resultat.append(minuscula ? Character.toLowerCase(substitut) : substitut);
            } else {
                resultat.append(c); 
            }
        }
        return resultat.toString();
    }

    
    public static String desxifraPoliAlfa(String msg) {
        StringBuilder resultat = new StringBuilder();
        int numAlfabets = permutations.length;
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            boolean minuscula = Character.isLowerCase(c);
            char base = Character.toUpperCase(c);

            int alfabetIndex = -1;
            char[] permActual = permutations[i % numAlfabets];

            
            for (int j = 0; j < permActual.length; j++) {
                if (permActual[j] == base) {
                    alfabetIndex = j;
                    break;
                }
            }

            if (alfabetIndex != -1) {
                char original = alfabet.charAt(alfabetIndex);
                resultat.append(minuscula ? Character.toLowerCase(original) : original);
            } else {
                resultat.append(c);
            }
        }
        return resultat.toString();
    }

    
    public static void main(String[] args) {
        initRandom(secretkey);

        
        permutations = new char[3][];
        for (int i = 0; i < permutations.length; i++) {
            permutations[i] = permutaAlfabet();
        }


        String msgs[] = { "Test 01 àrbitre, coixí, Perimetre", "Test 02 Taló, DÍa, año", "Test 03 Peca, Òrrios, Bòvilia"};
        String msgXifrat[] = new String[msgs.length];

        System.out.println("\nXifrat \n -------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(secretkey); // reset random for same permutations
            msgXifrat[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%34s => %s%n", msgs[i], msgXifrat[i]);
        }


        System.out.println("\nDesxifrat \n -----------" );
        for (int i = 0; i < msgXifrat.length; i++) {
            initRandom(secretkey); // reset random for same permutations
            String msg = desxifraPoliAlfa(msgXifrat[i]);
            System.out.printf("%34s => %s%n", msgXifrat[i], msg);
        }
    }

    
}
