import java.util.ArrayList;
import java.util.Collections;

public class Mäng {
    //siin võiks genereerida arve, mis tulevad mängu ajal
    private ArrayList<Integer> arvud = new ArrayList<>();
    private static boolean võitja = false;
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
    public void arvuGenreerija() throws InterruptedException{
        for(int i = 1;i<76;i++){
            arvud.add(i);
        }
        Collections.shuffle(arvud);
        for(int i = 0;i<75 && !võitja;i++){
            System.out.println("Loositi arv: "+arvud.get(i));
            System.out.println("Järgmine arv genereeritakse 5 sekundi pärast.");
            Thread.sleep(5000);
        }

    }


}
