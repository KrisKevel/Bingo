import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Lõpp {

    private BorderPane lõpp = new BorderPane();
    private String tulemused;
    private Button nuppUuesti;
    private Button nuppVälju;
    private Müüja m;
    private Stage pealava;
    private Group juur;


    public Lõpp(Stage pealava, Group juur) {
        this.pealava = pealava;
        this.juur = juur;
    }

    public BorderPane loomeL() {
        lõpp.setMinHeight(500.0);
        lõpp.setMinWidth(500.0);

        Text t1 = new Text("Tulemused:");
        Text t2 = new Text(tulemused);

        BorderPane üleval = new BorderPane();
        üleval.setCenter(t1);
        lõpp.setTop(üleval);
        lõpp.setCenter(t2);

        BorderPane bp = new BorderPane();
        BorderPane bp2 = new BorderPane();
        lõpp.setBottom(bp);
        bp.setCenter(bp2);

        bp2.setCenter(uuestiNupp());

        return lõpp;
    }

    public Button uuestiNupp() {
        nuppUuesti = new Button("Proovin uuesti");

        nuppUuesti.setOnMouseClicked(event -> {
            m = new Müüja(pealava, juur);
            juur.getChildren().add(m.getKirjelduseAken());
            m.setJuur(juur);
            m.getKirjelduseAken().setVisible(true);
            lõpp.setVisible(false);
        });

        return nuppUuesti;
    }


    public BorderPane getLõpp() {
        return lõpp;
    }


    public void setTulemused(String tulemused) {
        this.tulemused = tulemused;
    }

    public void setJuur(Group juur) {
        this.juur = juur;
    }
}
