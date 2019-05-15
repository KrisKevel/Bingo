import java.util.Arrays;

public class Mängija {

    private String nimi;
    private int[][] pilet;
    private String[][] piletKontrollitud = new String[5][5];
    private int[] mängijaVõidud = {0, 0, 0};
    private int punktid = 0;

    Mängija(String nimi, int[][] pilet) {
        this.nimi = nimi;
        this.pilet = pilet;
    }

    public int[] getMängijaVõidud() {
        return mängijaVõidud;
    }

    public void setMängijaVõidud(int[] mängijaVõidud) {
        this.mängijaVõidud = mängijaVõidud;
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

    public String[][] getPiletKontrollitud() {
        return piletKontrollitud;
    }

    public void setPiletKontrollitud(String[][] piletKontrollitud) {
        this.piletKontrollitud = piletKontrollitud;
    }

    public int getPunktid() {
        return punktid;
    }

    public void setPunktid(int punktid) {
        this.punktid = punktid;
    }

    @Override
    public String toString() {
        String piletSõnena = "\n";

        for (int[] rida : pilet) {
            piletSõnena += Arrays.toString(rida) + "\n";
        }

        return "Pilet = " + piletSõnena;
    }
}
