import java.util.ArrayList;
import java.util.List;

public class Bingo {

    public static void main(String[] args) {

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
        if(m.getNimi().equals("")){
            m.setNimi("Sander");
        }

        System.out.println(m.toString());

    }
}
