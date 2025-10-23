package iticbcn.xifratge;


public interface Xifrador {

    public TextXifratge xifra(String msg, String clau) throws ClauNoSuportada;

    public String desxifra(TextXifratge xifrat, String clau) throws ClauNoSuportada;
}
