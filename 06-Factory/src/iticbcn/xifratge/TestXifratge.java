package iticbcn.xifratge;

public class TestXifratge {
    
    public static void main(String[] args) {

        AlgorismeFactory[] aFactory = {
            new AlgorismeAES(),
            new AlgorismeMonoAlfabetic(),
            new AlgorismePolialfabetic(),
            new AlgorismeRotX()
        };

        String[] aNames = {
            "AES",
            "Monoalfabetic",
            "Polialfabetic",
            "RotX"
        }; 

        String[] msgs = {
            "Test 01: Àlgid, ülrich, Vàlid ",
            "Test 02: Caracters especials: !@#€%&/()=?",
            "Test 03: Prova final de xifratge amb l'algorisme "
        };

        String[][] claus = {
            {"Clau Secreta 01"},
            {"ErrorClau", null},
            {"ErrorClau2", "123456"},
            {"-1", "13", "1000", "ErrorClau"}
        };

        for (int i = 0; i < aFactory.length; i++) {
            AlgorismeFactory alg = aFactory[i];
            String nom = aNames[i];

            Xifrador xifrador = alg.creaXifrador();
            System.out.println("---------------");

            for(String msg : msgs) {
        
                for(String clau : claus[i]) {
                    System.out.println("Missatge original: " + msg);
                    System.out.println("Algorisme: " + nom);
                    System.out.println("Clau: " + clau);
                    TextXifratge tx = null;
                    try {
                        tx = xifrador.xifra(msg, clau); 
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                    System.out.println("TextXifrat:" + tx);

                    String desxifrat = null;

                    try{
                        desxifrat = xifrador.desxifra(tx, clau);
                    }catch(ClauNoSuportada e){
                        System.err.println(e.getLocalizedMessage());
                    }
                    System.out.println("desxifrat:" + desxifrat);
                    System.out.println("-----------------");
                }
        }
    }
}
