import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Bingo {
    private static int võitudeArvMängus = 0;
    private static boolean võitja = false;

    public static void main(String[] args) throws InterruptedException{
        Mäng mäng = new Mäng();
        mäng.arvuGenereerija();
        ArrayList<Integer> genereeritavadArvud = mäng.getArvud();//Genereerib loositavate nr-ite listi,

        Müüja müüja = new Müüja();
        LotoMasin masin = new LotoMasin();

        // lisasin siia mängijaid, et mängiks mitte ainult kasutaja,
        // vaid ka teised välja mõeldud inimesed
        Mängija m1 = new Mängija("Peeter", masin.genereeriPilet());
        Mängija m2 = new Mängija("Maria", masin.genereeriPilet());
        Mängija m3 = new Mängija("Toomas", masin.genereeriPilet());
        Mängija m4 = new Mängija("Laura", masin.genereeriPilet());

        List<Mängija> mängijad = new ArrayList<>();
        mängijad.add(m1);
        mängijad.add(m2);
        mängijad.add(m3);
        mängijad.add(m4);

        String vastus = müüja.tervitus();

        Mängija m = new Mängija(vastus, masin.genereeriPilet());

        //kui kasutaja ei soovi mängida, mängib Sander
        if (m.getNimi().equals("")) {
            m.setNimi("Sander");
        }

        System.out.println(m.toString());
        do {
            mäng.setArveLoositud(mäng.getArveLoositud() + 1);
            System.out.println("Loositi arv: " + genereeritavadArvud.get(mäng.getArveLoositud()));
            for (Mängija elem : mängijad) {
                Pilet.kontrolliNumbrit(elem, mäng);
            }
            if (mäng.getArveLoositud() > 3) {
                for(Mängija elem:mängijad){
                    int[] ennekontrolliVõidud = elem.getMängijaVõidud();
                    Pilet.kontrolliPiletit(elem);
                    if(ennekontrolliVõidud[0] < elem.getMängijaVõidud()[0]){
                        System.out.println("Mängija "+elem.getNimi()+" sai diagonaali täis.");
                    }
                    else if(ennekontrolliVõidud[1] < elem.getMängijaVõidud()[1]){
                        System.out.println("Mängija "+elem.getNimi()+" sai rea täis.");
                    }
                    else if(ennekontrolliVõidud[2] < elem.getMängijaVõidud()[2]){
                        System.out.println("Mängija "+elem.getNimi()+" sai veeru täis.");
                    }
                }
            }
            Pilet.kontrolliNumbrit(m,mäng);// ei saa teda listi panna, muidu kontrollitaks pileti võitu ise ja mängija ei saagi midagi teha pmst.
            //vajaks paremat seletust ilmselt aga teen esialgu nii.
            System.out.println("Kui soovite järgmist numbrit, sisetage 1.Kui arvate, et saite diagonaali/rea/veeru -2,kui täismäng -3 ");
            Scanner sisestus = new Scanner(System.in);
            String kasutajaVaste = sisestus.nextLine();
            if(kasutajaVaste.equals("1")){
                return;
            }
            else if(kasutajaVaste.equals("2")){
                int[] ennekontrolliVõidud = m.getMängijaVõidud();
                Pilet.kontrolliPiletit(m);
                if(ennekontrolliVõidud[0] < m.getMängijaVõidud()[0]){
                    System.out.println("Mängija "+m.getNimi()+" sai diagonaali täis.");
                }
                else if(ennekontrolliVõidud[1] < m.getMängijaVõidud()[1]){
                    System.out.println("Mängija "+m.getNimi()+" sai rea täis.");
                }
                else if(ennekontrolliVõidud[2] < m.getMängijaVõidud()[2]){
                    System.out.println("Mängija "+m.getNimi()+" sai veeru täis.");
                }
                else{
                    //midagi sellist et kaotab punkte vms kui arvab et bingo aga tglt mitte.
                    System.out.println("Mängija "+m.getNimi()+" kuulutas Bingo liiga vara, kaotab punkte." );
                }
            }
            else if(kasutajaVaste.equals("3")){
                Pilet.kontrolliPiletit(m);
                if(m.getMängijaVõidud()[0] == 2 && m.getMängijaVõidud()[1]== 5 && m.getMängijaVõidud()[2]==5){
                    System.out.println("Mängija "+m.getNimi()+" võitis!");
                }
                else{
                    //midagi sellist et kaotab punkte vms kui arvab et bingo aga tglt mitte.
                    System.out.println("Mängija "+m.getNimi()+" kuulutas Bingo liiga vara, kaotab punkte." );
                }
            }
        }
        while (!võitja);

    }
}
