import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotoMasin {

    public int[][] genereeriPilet() {

        //meil on vaja luua massiivi 5x5 ja lisada
        //sinna 25 unikaalset numbrit vahemikust 1-75


        //muutsin seda nii, et esimeses veerus on nr 1-15, teises 16-30 jne
        //nii on palju lihtsam jälgida

        List<Integer> numbrid1 = new ArrayList<>();
        List<Integer> numbrid2 = new ArrayList<>();
        List<Integer> numbrid3 = new ArrayList<>();
        List<Integer> numbrid4 = new ArrayList<>();
        List<Integer> numbrid5 = new ArrayList<>();
        int[][] pilet = new int[5][5];

        for (int i = 1; i < 16; i++) {
            numbrid1.add(i);
        }
        for (int i = 16; i < 31; i++) {
            numbrid2.add(i);
        }
        for (int i = 31; i < 46; i++) {
            numbrid3.add(i);
        }
        for (int i = 46; i < 61; i++) {
            numbrid4.add(i);
        }
        for (int i = 61; i < 76; i++) {
            numbrid5.add(i);
        }

        Collections.shuffle(numbrid1);
        Collections.shuffle(numbrid2);
        Collections.shuffle(numbrid3);
        Collections.shuffle(numbrid4);
        Collections.shuffle(numbrid5);

        //ja nüüd paigutan esimesi numbreid massiivi
        for (int i = 0; i < 5; i++) {
            pilet[i][0] = numbrid1.get(i);
            pilet[i][1] = numbrid2.get(i);
            pilet[i][2] = numbrid3.get(i);
            pilet[i][3] = numbrid4.get(i);
            pilet[i][4] = numbrid5.get(i);
        }

        return pilet;
    }

}
