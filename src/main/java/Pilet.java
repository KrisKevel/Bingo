public class Pilet {

    private int[][] pilet;

    Pilet(int[][] pilet) {

        this.pilet = pilet;
    }

    public int[][] getPilet() {
        return pilet;
    }

    public static void kontrolliNumbrit(Mängija mängija, Mäng praeguneMäng) {
        String[][] kontrollitudOsa = mängija.getPiletKontrollitud();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mängija.getPilet()[i][j] == praeguneMäng.getArvud().get(praeguneMäng.getArveLoositud())) {
                    kontrollitudOsa[i][j] = "X";
                }

            }
        }

        mängija.setPiletKontrollitud(kontrollitudOsa);
    }


    public static int[] kontrolliPiletit(Mängija mängija) {
        int[] võit = {0, 0, 0};//[0] diagonaalide võidud,[1]ridade võidud,[2]veergude võidud täisvõit == {2,5,5} listi puhul
        String[][] pilet = mängija.getPiletKontrollitud();


        for (int i = 0; i < pilet.length; i++) {
            for (int j = 0; j < pilet.length; j++) {
                if (pilet[i][j] != null && pilet[i][j].equals("X")) {
                    if (j == 0) {
                        if (i == 0) {
                            if (pilet[i][j].equals(pilet[i + 1][j + 1]) && pilet[i + 1][j + 1].equals(pilet[i + 2][j + 2]) && pilet[i + 2][j + 2].equals(pilet[i + 3][j + 3]) && pilet[i + 3][j + 3].equals(pilet[i + 4][j + 4]))
                                võit[0] += 1;

                            if (pilet[i][j].equals(pilet[i + 1][j]) && pilet[i + 1][j].equals(pilet[i + 2][j]) && pilet[i + 2][j].equals(pilet[i + 3][j]) && pilet[i + 3][j].equals(pilet[i + 4][j]))
                                võit[2] += 1;
                        } else if (i == 4)
                            if ((pilet[i][j].equals(pilet[i - 1][j + 1]) && pilet[i - 1][j + 1].equals(pilet[i - 2][j + 2]) && pilet[i - 2][j + 2].equals(pilet[i - 3][j + 3]) && pilet[i - 3][j + 3].equals(pilet[i - 4][j + 4])))
                                võit[0] += 1;


                        if (pilet[i][j].equals(pilet[i][j + 1]) && pilet[i][j + 1].equals(pilet[i][j + 2]) && pilet[i][j + 2].equals(pilet[i][j + 3]) && pilet[i][j + 3].equals(pilet[i][j + 4]))
                            võit[1] += 1;
                    }

                    if (i == 0)
                        if (pilet[i][j].equals(pilet[i + 1][j]) && pilet[i + 1][j].equals(pilet[i + 2][j]) && pilet[i + 2][j].equals(pilet[i + 3][j]) && pilet[i + 3][j].equals(pilet[i + 4][j]))
                            võit[2] += 1;
                }
            }
        }

        mängija.setMängijaVõidud(võit);

        return võit;
    }

}
