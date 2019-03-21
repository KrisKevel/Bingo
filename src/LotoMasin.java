import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotoMasin {

    public int[][] genereeriPilet(){

        //meil on vaja luua massiivi 5x5 ja lisada
        //sinna 25 unikaalset numbrit vahemikust 1-75

        List<Integer> numbrid = new ArrayList<>();
        int[][] pilet = new int[5][5];
        int indeks = 0;

        //loon massiivi numbritega 1-75
        for(int i = 1; i < 76; i++){
            numbrid.add(i);
        }

        //paigutan neid juhuslikult ümber
        Collections.shuffle(numbrid);

        //ja nüüd paigutan esimesi pileti massiivi
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                pilet[i][j] = numbrid.get(indeks);
                indeks++;
            }
        }

        return pilet;
    }

}
