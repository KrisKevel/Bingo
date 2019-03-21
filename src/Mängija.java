import java.util.Arrays;

public class Mängija {

    String nimi;
    int[][] pilet;

    Mängija(String nimi, int[][] pilet){
        this.nimi = nimi;
        this.pilet = pilet;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int[][] getPilet() {
        return pilet;
    }

    public void setPilet(int[][] piletiN) {
        this.pilet = piletiN;
    }

    //mängija võiks kontrollida oma piletit mängu ajal



    @Override
    public String toString() {
        String piletSõnena = "\n";

        for(int[] rida: pilet){
            piletSõnena += Arrays.toString(rida) + "\n";
        }

        return "Pilet = " + piletSõnena;
    }
}
