import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bingo {
    private static boolean võitja = false;

    public static void main(String[] args) throws InterruptedException {
        Mäng mäng = new Mäng();
        Mäng.arvuGenereerija();
        ArrayList<Integer> genereeritavadArvud = mäng.getArvud();//Genereerib loositavate nr-ite listi,

        Müüja müüja = new Müüja();
        LotoMasin masin = new LotoMasin();

        // lisasin siia mängijaid, et mängiks mitte ainult kasutaja,
        // vaid ka teised välja mõeldud inimesed
        Mängija m1 = new Mängija("Peeter", masin.genereeriPilet());
        Mängija m2 = new Mängija("Maria", masin.genereeriPilet());
        Mängija m3 = new Mängija("Toomas", masin.genereeriPilet());
        Mängija m4 = new Mängija("Laura", masin.genereeriPilet());
        Mängija m5 = new Mängija("Erkki", masin.genereeriPilet());
        Mängija m6 = new Mängija("Rasmus", masin.genereeriPilet());
        Mängija m7 = new Mängija("Olga", masin.genereeriPilet());
        Mängija m8 = new Mängija("Margus", masin.genereeriPilet());

        List<Mängija> mängijad = new ArrayList<>();
        mängijad.add(m1);
        mängijad.add(m2);
        mängijad.add(m3);
        mängijad.add(m4);
        mängijad.add(m5);
        mängijad.add(m6);
        mängijad.add(m7);
        mängijad.add(m8);

        String vastus = müüja.tervitus();

        Mängija m = new Mängija(vastus, masin.genereeriPilet());

        //kui kasutaja ei soovi mängida, mängib Sander
        if (m.getNimi().equals("")) {
            m.setNimi("Sander");
        }

        System.out.println(m.toString());

        do {
            Mäng.setArveLoositud(Mäng.getArveLoositud() + 1);
            System.out.println("Loositi arv: " + genereeritavadArvud.get(Mäng.getArveLoositud()));

            for (Mängija elem : mängijad) {
                Pilet.kontrolliNumbrit(elem, mäng);
            }

            if (Mäng.getArveLoositud() > 3) {
                int[] võiduKombinatsioon = {2, 5, 5};

                //Siin veits muutsin, kuna muidu mäng käis kuni kõik numbrid olid välja kuulutatud
                //Nüüd, kui keegi võidab, tsükkel lõpeb.

                for (Mängija elem : mängijad) {
                    int[] ennekontrolliVõidud = elem.getMängijaVõidud();
                    Pilet.kontrolliPiletit(elem);

                    if (ennekontrolliVõidud[0] < elem.getMängijaVõidud()[0]) {
                        System.out.println("Mängija " + elem.getNimi() + " sai diagonaali täis.");
                        elem.setPunktid(elem.getPunktid() + 50);
                    }

                    if (ennekontrolliVõidud[1] < elem.getMängijaVõidud()[1]) {
                        System.out.println("Mängija " + elem.getNimi() + " sai rea täis.");
                        elem.setPunktid(elem.getPunktid() + 10);
                    }

                    if (ennekontrolliVõidud[2] < elem.getMängijaVõidud()[2]) {
                        System.out.println("Mängija " + elem.getNimi() + " sai veeru täis.");
                        elem.setPunktid(elem.getPunktid() + 10);
                    }

                    if(elem.getMängijaVõidud()[1] == 5 || elem.getMängijaVõidud()[2] == 5){
                        System.out.println("Mängija " + elem.getNimi() + " võitis!");
                        elem.setPunktid(elem.getPunktid() + 10000);
                        võitja = true;
                    }
                }
            }

            Pilet.kontrolliNumbrit(m, mäng);// ei saa teda listi panna, muidu kontrollitaks pileti võitu ise ja mängija ei saagi midagi teha pmst.
            //vajaks paremat seletust ilmselt aga teen esialgu nii.
            System.out.println("Kui soovite järgmist numbrit, sisetage \"1\". Kui arvate, et saite diagonaali/rea/veeru - \"2\", kui täismäng - \"3\".");
            Scanner sisestus = new Scanner(System.in);
            String kasutajaVaste = sisestus.nextLine();

            if (kasutajaVaste.equals("1")) { }

            else if (kasutajaVaste.equals("2")) {
                int võite = 0;

                int[] ennekontrolliVõidud = m.getMängijaVõidud();
                Pilet.kontrolliPiletit(m);

                if (ennekontrolliVõidud[0] < m.getMängijaVõidud()[0]) {
                    System.out.println("Mängija " + m.getNimi() + " sai diagonaali täis.");
                    võite++;
                    m.setPunktid(m.getPunktid() + 50);
                }

                if (ennekontrolliVõidud[1] < m.getMängijaVõidud()[1]) {
                    System.out.println("Mängija " + m.getNimi() + " sai rea täis.");
                    võite++;
                    m.setPunktid(m.getPunktid() + 10);
                }

                if (ennekontrolliVõidud[2] < m.getMängijaVõidud()[2]) {
                    System.out.println("Mängija " + m.getNimi() + " sai veeru täis.");
                    võite++;
                    m.setPunktid(m.getPunktid() + 10);
                }

                if (võite == 0) {
                    //midagi sellist et kaotab punkte vms kui arvab et bingo aga tglt mitte.
                    // tõin randomi sisse , 1/10 chance, et ei kaota punkte kui kuulutab bingo
                    double juhuArv = (double)Math.random()*10;
                    int juhuArvIntina = (int) juhuArv;
                    if(juhuArvIntina==1){
                        System.out.println("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, aga vedas, sest mängu läbiviija ei märganud seda" +
                                "ja punkte maha ei võetud.");
                    }
                    else{
                        System.out.println("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, kaotab punkte.");

                        if(m.getPunktid() >= 10)
                            m.setPunktid(m.getPunktid() - 10);

                        else
                            m.setPunktid(0);
                    }
                }

                System.out.println();

            }


            else if (kasutajaVaste.equals("3")) {
                Pilet.kontrolliPiletit(m);
                if (m.getMängijaVõidud()[1] == 5 || m.getMängijaVõidud()[2] == 5) {
                    System.out.println("Mängija " + m.getNimi() + " võitis!");
                    m.setPunktid(m.getPunktid() + 10000);
                    võitja = true;

                } else {
                    //midagi sellist et kaotab punkte vms kui arvab et bingo aga tglt mitte.
                    // tõin randomi sisse , 1/10 chance, et ei kaota punkte kui kuulutab bingo
                    double juhuArv = (double)Math.random()*10;
                    int juhuArvIntina = (int) juhuArv;
                    if(juhuArvIntina==1){
                        System.out.println("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, aga vedas, sest mängu läbiviija ei märganud seda" +
                                "ja punkte maha ei võetud.");
                    }
                    else{
                        System.out.println("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, kaotab punkte.");

                        juhuArv = Math.random()*10;
                        juhuArvIntina = (int) juhuArv;
                        if(juhuArvIntina==1){
                            System.out.println("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, aga vedas, sest mängu läbiviija ei märganud seda" +
                                    "ja punkte maha ei võetud.");
                        }

                        else{
                            if(m.getPunktid() >= 50)
                                m.setPunktid(m.getPunktid() - 50);

                            else
                                m.setPunktid(0);
                        }
                    }
                }

                System.out.println();
            }


            if(Mäng.getArveLoositud() + 1 == 75){
                System.out.println("Kõik arvud on loositud.");
                võitja = true;
            }
        }
        while (!võitja);

        võidud(mängijad);
        System.out.println(m.getNimi() + " võitis " + m.getPunktid() + " eurot.");

    }

    public static void võidud(List<Mängija> mängijad){

        for(Mängija mängija: mängijad){
            System.out.println(mängija.getNimi() + " võitis " + mängija.getPunktid() + " eurot.");
        }
    }
}
