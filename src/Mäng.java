import java.util.ArrayList;
import java.util.Collections;

public class Mäng {
    //siin võiks genereerida arve, mis tulevad mängu ajal
    // muuda staatilisust
    private ArrayList<Integer> arvud = new ArrayList<>();
    private int arveLoositud = -1;
    private boolean võitja = false;
    private int võitudeArvMängus = 0;

    public void arvuGenereerija() {
        ArrayList<Integer> arvud = new ArrayList<>();
        for (int i = 1; i < 76; i++) {
            arvud.add(i);
        }
        Collections.shuffle(arvud);
        setArvud(arvud);
    }

    public void setArvud(ArrayList<Integer> arvud) {
        this.arvud = arvud;
    }

    public int getArveLoositud() {
        return arveLoositud;
    }

    public void setArveLoositud(int arveLoositud) {
        this.arveLoositud = arveLoositud;
    }

    public boolean isVõitja() {
        return võitja;
    }

    public void setVõitja(boolean võitja) {
        this.võitja = võitja;
    }

    public ArrayList<Integer> getArvud() {
        return arvud;
    }

    public int getVõitudeArvMängus() {
        return võitudeArvMängus;
    }
}
