import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Müüja {

    private BorderPane kirjelduseAken = kirjeldus();
    private Button edasi;
    private TextField vastuseVäli;
    private Stage pealava;
    private Peaaken pea;
    private Group juur;

    public Müüja(Stage pealava, Group juur) {
        this.pealava = pealava;
        this.juur = juur;
    }

    public BorderPane kirjeldus() throws NullPointerException {

        kirjelduseAken = new BorderPane();
        kirjelduseAken.setMinHeight(500.0);
        kirjelduseAken.setMinWidth(500.0);

        BorderPane bp = new BorderPane();
        kirjelduseAken.setCenter(bp);
        Text kirjeldus = new Text(
                "             Armas kasutaja,\n" +
                        "Pakume sulle unikaalset võimalust\n" +
                        "        tasuta mängida Bingot\n" +
                        "  ja võita päris *ahem-ahem* raha.\n" +
                        "           Mis on sinu nimi?");
        vastuseVäli = new TextField();
        bp.setCenter(kirjeldus);
        bp.setBottom(vastuseVäli);

        BorderPane bp2 = new BorderPane();
        kirjelduseAken.setBottom(bp2);

        edasi = edasiNupp();
        bp2.setCenter(edasi);

        return kirjelduseAken;
    }

    public Button edasiNupp() throws NullPointerException {
        Button nuppEdasi = new Button("Mängi!");

        nuppEdasi.setOnMouseClicked(event -> {
            if(getVastus().equals("")){
                throw new IllegalArgumentException("Nimi ei saa olla tühi");
            }
                    pea = new Peaaken(pealava, juur, this);
            //pea.looPiletidJaMängijad();
                    juur.getChildren().add(pea.getPeaaken());
                    pea.setJuur(juur);
                    pea.getPeaaken().setVisible(true);
                    getKirjelduseAken().setVisible(false);
                }
        );

        return nuppEdasi;
    }

    public BorderPane getKirjelduseAken() {
        return kirjelduseAken;
    }

    public String getVastus() {
        return vastuseVäli.getText();
    }

    public void setJuur(Group juur) {
        this.juur = juur;
    }
}
