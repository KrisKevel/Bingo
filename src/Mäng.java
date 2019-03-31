import java.util.ArrayList;
import java.util.Collections;

public class Mäng {
    //siin võiks genereerida arve, mis tulevad mängu ajal
    private static ArrayList<Integer> arvud = new ArrayList<>();
    private static int arveLoositud = -1;
    private static boolean võitja = false;
    private static int võitudeArvMängus = 0;
    //arvud siis mida mängu jooksul genereerima hakatakse
    //teen sama mis lotomasina pmst
    // Mõtlen et teeks äkki nii, et genereerib arvud, shuffleb
    // ja siis hakkab neid ükshaaval välja printima vms, ja nagu mingi
    //delayga, ja ss kui mängija näeb et tal mingi bingo siis peab
    //kirjutama kuidagi"bingo" vms ja ss see kontrollib kas on
    // ja kui ei ole saab mingi karistuse vms ,kui on võidab raha vms
    // Ehk siis kui arvuti mängib ka, peaksime isendimeetodi tegema,
    //et kui saab bingo siis ta declareb selle ka, ja kui keegi nn.hüüab
    //bingo siis see 'võitja' boolean läheb True-ks ja enam arve ei prindita.
    public static void arvuGenereerija(){
        for(int i = 1;i<76;i++){
            arvud.add(i);
        }
        Collections.shuffle(arvud);
        /*for(int i = 0;i<75 && !võitja;i++){
            arveLoositud +=1;
            System.out.println("Loositi arv: "+arvud.get(i));
            System.out.println("Järgmine arv genereeritakse 5 sekundi pärast.");
            Thread.sleep(5000);
        }*/
    }

    public ArrayList<Integer> getArvud() {
        return arvud;
    }

    public int getVõitudeArvMängus() {
        return võitudeArvMängus;
    }

    public static int getArveLoositud() {
        return arveLoositud;
    }

    public static void setArveLoositud(int arveLoositud) {
        Mäng.arveLoositud = arveLoositud;
    }

    public static boolean isVõitja() {
        return võitja;
    }

    public static void setVõitja(boolean võitja) {
        Mäng.võitja = võitja;
    }
}
