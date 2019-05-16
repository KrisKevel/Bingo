import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Peaaken {

    private BorderPane peaaken = loomePA();
    private Stage pealava;
    private Group juur;
    private Müüja müüja;
    private Lõpp lõpp = new Lõpp(pealava, juur);
    private Teated teadeteTahvel = new Teated(new ArrayList<>());
    private boolean onTeateid = false;

    private Button nuppAlgus;
    private Button nuppLõpp;
    private Button nuppJärgmineNumber;
    private Button nuppRidaVeergVõiDiagonaal;
    private Button nuppBingo;
    private VBox nupud;


    public Peaaken(Stage pealava, Group juur, Müüja m) {
        this.pealava = pealava;
        this.juur = juur;
        this.müüja = m;
    }

    public BorderPane loomePA() {
        peaaken = new BorderPane(); //põhi, kuhu saame kõike lisada
        peaaken.setMinHeight(500.0);
        peaaken.setMinWidth(500.0);



        peaaken.setTop(algusNupp());

        nupud = new VBox();
        nupud.setPrefWidth(180);
        nupud.getChildren().addAll(järgmineNumber(), ridaVeergVõiDiagonaal(), bingoNupp());

        return peaaken;
    }

    public Button tulemusedNupp() {
        nuppLõpp = new Button("Tulemused!");

        nuppLõpp.setOnMouseClicked(event -> {
            lõpp.loomeL();
            juur.getChildren().add(lõpp.getLõpp());
            lõpp.setJuur(juur);
            lõpp.getLõpp().setVisible(true);
            peaaken.setVisible(false);
        });

        return nuppLõpp;
    }

    public Button algusNupp() {
        nuppAlgus = new Button("Alusta mänguga!");

        nuppAlgus.setOnMouseClicked(event -> {
            looPiletidJaMängijad();
            peaaken.setTop(nupud);
            GridPane gp = new GridPane(); //see võiks mängija pilet olla?
            gp.setMinSize(250,250);
            gp.setPadding(new Insets(0, 10, 0, 10));
            gp.setHgap(10);
            gp.setVgap(10);
            gp.setAlignment(Pos.CENTER_LEFT);
            int[][] pilet = m.getPilet();
            //Text Mangija = new Text("Mängija "+m.getNimi()+" pilet.");
           // Mangija.setFont(Font.font("Arial",FontWeight.BOLD,10));
            for(int i = 0;i<5;i++){
                for(int j = 0;j<5;j++){
                    Pane pane = new Pane();
                    pane.setOnMouseReleased(e -> {
                        if(pane.getStyle().equals("-fx-background-color: green;")){
                            pane.setStyle("-fx-background-color: white;");
                        }
                        else {
                            pane.setStyle("-fx-background-color: green;");
                        }
                    });
                    pane.getChildren().addAll(new Text(Integer.toString(pilet[j][i])));
                    gp.add(pane,i,j);
                }
            }
            peaaken.setLeft(gp);
        });

        return nuppAlgus;
    }

    public Button järgmineNumber() {
        nuppJärgmineNumber = new Button("Järgmine number");
        nuppJärgmineNumber.setMinWidth(180);

        nuppJärgmineNumber.setOnMouseClicked(event ->
        {
            mängi();
        });

        return nuppJärgmineNumber;
    }

    public Button ridaVeergVõiDiagonaal() {
        nuppRidaVeergVõiDiagonaal = new Button("Sain diagonaali/veeru/rea");
        nuppRidaVeergVõiDiagonaal.setMinWidth(180);

        nuppRidaVeergVõiDiagonaal.setOnMouseClicked(event -> {
            int võite = 0;

            int[] ennekontrolliVõidud = m.getMängijaVõidud();
            Pilet.kontrolliPiletit(m);

            if (ennekontrolliVõidud[0] < m.getMängijaVõidud()[0]) {
                teadeteTahvel.lisaTeade("Mängija " + m.getNimi() + " sai diagonaali täis.");
                võite++;
                m.setPunktid(m.getPunktid() + 50);
            }

            if (ennekontrolliVõidud[1] < m.getMängijaVõidud()[1]) {
                teadeteTahvel.lisaTeade("Mängija " + m.getNimi() + " sai rea täis.");
                võite++;
                m.setPunktid(m.getPunktid() + 10);
            }

            if (ennekontrolliVõidud[2] < m.getMängijaVõidud()[2]) {
                teadeteTahvel.lisaTeade("Mängija " + m.getNimi() + " sai veeru täis.");
                võite++;
                m.setPunktid(m.getPunktid() + 10);
            }

            if (võite == 0) {
                //midagi sellist et kaotab punkte vms kui arvab et bingo aga tglt mitte.
                // tõin randomi sisse , 1/10 chance, et ei kaota punkte kui kuulutab bingo
                double juhuArv = Math.random() * 10;
                int juhuArvIntina = (int) juhuArv;
                if (juhuArvIntina == 1) {
                    teadeteTahvel.lisaTeade("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, aga vedas,\nsest mängu läbiviija ei märganud seda" +
                            " ja punkte maha ei võetud.");

                } else {
                    teadeteTahvel.lisaTeade("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, kaotab punkte.");

                    if (m.getPunktid() >= 10)
                        m.setPunktid(m.getPunktid() - 10);

                    else
                        m.setPunktid(0);
                }
            }

            mängi();
        });

        return nuppRidaVeergVõiDiagonaal;
    }


    public Button bingoNupp() {
        nuppBingo = new Button("Bingo!");
        nuppBingo.setMinWidth(180);

        nuppBingo.setOnMouseClicked(event -> {
            Pilet.kontrolliPiletit(m);
            if (m.getMängijaVõidud()[1] == 5 || m.getMängijaVõidud()[2] == 5) {
                m.setPunktid(m.getPunktid() + 10000);
                lõpetaMängu();

            } else {
                //midagi sellist et kaotab punkte vms kui arvab et bingo aga tglt mitte.
                // tõin randomi sisse , 1/10 chance, et ei kaota punkte kui kuulutab bingo
                double juhuArv = Math.random() * 10;
                int juhuArvIntina = (int) juhuArv;
                if (juhuArvIntina == 1) {
                    teadeteTahvel.lisaTeade("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, aga vedas,\nsest mängu läbiviija ei märganud seda" +
                            " ja punkte maha ei võetud.");

                } else {
                    teadeteTahvel.lisaTeade("Mängija " + m.getNimi() + " kuulutas Bingo liiga vara, kaotab punkte.");

                    if (m.getPunktid() >= 50)
                        m.setPunktid(m.getPunktid() - 50);

                    else
                        m.setPunktid(0);
                }

                mängi();
            }
        });

        return nuppBingo;
    }


    private Mäng mäng;
    private List<Mängija> mängijad;
    private Mängija m;
    private ArrayList<Integer> genereeritavadArvud;

    public void looPiletidJaMängijad() {
        peaaken.getChildren().remove(nuppAlgus);

        mäng = new Mäng();
        mäng.arvuGenereerija();
        genereeritavadArvud = mäng.getArvud();//Genereerib loositavate nr-ite listi,

        LotoMasin masin = new LotoMasin();

        Mängija m1 = new Mängija("Peeter", masin.genereeriPilet());
        Mängija m2 = new Mängija("Maria", masin.genereeriPilet());
        Mängija m3 = new Mängija("Toomas", masin.genereeriPilet());
        Mängija m4 = new Mängija("Laura", masin.genereeriPilet());
        Mängija m5 = new Mängija("Erkki", masin.genereeriPilet());
        Mängija m6 = new Mängija("Rasmus", masin.genereeriPilet());
        Mängija m7 = new Mängija("Olga", masin.genereeriPilet());
        Mängija m8 = new Mängija("Margus", masin.genereeriPilet());

        mängijad = new ArrayList<>();
        mängijad.add(m1);
        mängijad.add(m2);
        mängijad.add(m3);
        mängijad.add(m4);
        mängijad.add(m5);
        mängijad.add(m6);
        mängijad.add(m7);
        mängijad.add(m8);

        String vastus = müüja.getVastus();

        m = new Mängija(vastus, masin.genereeriPilet());
    }


    public void mängi() {

        mäng.setArveLoositud(mäng.getArveLoositud() + 1);
        if(teadeteTahvel.getTeadeteList().size()!=0){
            GridPane gp2 = new GridPane();
            gp2.setMinSize(250,250);
            gp2.setPadding(new Insets(0, 10, 10, 10));
            for(int i = 0;i<teadeteTahvel.getTeadeteList().size();i++){
                gp2.add(new Text(teadeteTahvel.getTeadeteList().get(i)),0,i);
            }
            peaaken.setBottom(gp2);
            teadeteTahvel.setTeadeteList(new ArrayList<>());
        }
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(0, 30, 0, 10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER_RIGHT);
        Text loositudNumber = new Text("Loositi arv: "+genereeritavadArvud.get(mäng.getArveLoositud()));
        loositudNumber.setFont(Font.font("Arial",FontWeight.BOLD,20));
        gp.add(loositudNumber,0,0);
        peaaken.setRight(gp);
        System.out.println("Loositi arv: " + genereeritavadArvud.get(mäng.getArveLoositud()));

        for (Mängija elem : mängijad) {
            Pilet.kontrolliNumbrit(elem, mäng);
        }

        if (mäng.getArveLoositud() > 3) {

            for (Mängija elem : mängijad) {
                int[] ennekontrolliVõidud = elem.getMängijaVõidud();
                Pilet.kontrolliPiletit(elem);

                if (ennekontrolliVõidud[0] < elem.getMängijaVõidud()[0]) {
                    teadeteTahvel.lisaTeade("Mängija " + elem.getNimi() + " sai diagonaali täis.");
                    elem.setPunktid(elem.getPunktid() + 50);
                }

                if (ennekontrolliVõidud[1] < elem.getMängijaVõidud()[1]) {
                    teadeteTahvel.lisaTeade("Mängija " + elem.getNimi() + " sai rea täis");
                }

                if (ennekontrolliVõidud[2] < elem.getMängijaVõidud()[2]) {
                    teadeteTahvel.lisaTeade("Mängija " + elem.getNimi() + " sai veeru täis.");
                    elem.setPunktid(elem.getPunktid() + 10);
                }

                if (elem.getMängijaVõidud()[1] == 5 || elem.getMängijaVõidud()[2] == 5) {
                    teadeteTahvel.lisaTeade("Mängija " + elem.getNimi() + " võitis!");
                    elem.setPunktid(elem.getPunktid() + 10000);
                    lõpetaMängu();
                }
            }
        }

        Pilet.kontrolliNumbrit(m, mäng);

        if (mäng.getArveLoositud() + 1 == 75) {
            lõpetaMängu();
        }

        võidud(mängijad, lõpp);
    }

    public void lõpetaMängu() {
        Text teavitaLõpust = new Text("Mäng on lõppenud.");
        nuppJärgmineNumber.setVisible(false);
        nuppBingo.setVisible(false);
        nuppRidaVeergVõiDiagonaal.setVisible(false);

        BorderPane bp = new BorderPane();
        peaaken.setBottom(bp);
        bp.setRight(tulemusedNupp());
        bp.setCenter(teavitaLõpust);

    }


    public void võidud(List<Mängija> mängijad, Lõpp lõpp) {
        String tulemused = m.getNimi() + " võitis " + m.getPunktid() + " eurot." + '\n';

        for (Mängija mängija : mängijad) {
            tulemused += (mängija.getNimi() + " võitis " + mängija.getPunktid() + " eurot." + '\n');
        }

        lõpp.setTulemused(tulemused);
    }


    public BorderPane getPeaaken() {
        return peaaken;
    }

    public void setJuur(Group juur) {
        this.juur = juur;
    }
}
