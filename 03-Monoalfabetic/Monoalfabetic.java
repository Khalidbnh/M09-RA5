
public class Monoalfabetic {


    public static final String Original = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZàáèéìíòóùúüÿçÀÁÈÉÌÍÒÓÙÚÜŸÇ";
    public static final String Permuted = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMáàéèíìóòúùüÿçÁÀÉÈÍÌÓÒÚÙÜŸÇ";


    public static String cipher(String text){
        StringBuilder result = new StringBuilder();

        for (char c: text.toCharArray()){
            if(Character.isLowerCase(c)){
                int index = Original.indexOf(c);
                if(index != -1){
                    result.append(Permuted.charAt(index));
                }
                else{
                    result.append(c);
                }

            } else if (Character.isUpperCase(c)){
                int index = Original.indexOf(Character.toLowerCase(c));
                if(index != -1){
                    char encrypted = Permuted.charAt(index);
                    result.append(Character.toUpperCase(encrypted));
                }
                else{
                    result.append(c);
                }
            } else{
                result.append(c);
            }
        }

        return result.toString();
    }



    public static String decipher(String text) {
    StringBuilder result = new StringBuilder();
    for (char c : text.toCharArray()) {
        if (Character.isLowerCase(c)) {
            int index = Permuted.indexOf(c);
            if (index != -1) {
                result.append(Original.charAt(index));
            } else {
                result.append(c);
            }
        } else if (Character.isUpperCase(c)) {
            int index = Permuted.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                char decrypted = Original.charAt(index);
                result.append(Character.toUpperCase(decrypted));
            } else {
                result.append(c);
            }
        } else {
            result.append(c);
        }
    }
     return result.toString();
    }



    public static void main(String[] args) {
        
        String message = "Hello World";
        String encrypted = cipher(message);
        String decrypted = decipher(encrypted);

        System.out.println("Original:  " + message);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
    }

}
