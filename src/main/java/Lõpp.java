import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Lõpp {

    private BorderPane lõpp = new BorderPane();
    private String tulemused;
    private Map<String,Integer> tulemusedSalvestamiseks = new HashMap<>();
    private Button nuppUuesti;
    private Button nuppVälju;
    private Müüja m;
    private Stage pealava;
    private Group juur;


    public Lõpp(Stage pealava, Group juur) {
        this.pealava = pealava;
        this.juur = juur;
    }

    public Map<String, Integer> getTulemusedSalvestamiseks() {
        return tulemusedSalvestamiseks;
    }

    public void setTulemusedSalvestamiseks(Map<String, Integer> tulemusedSalvestamiseks) {
        this.tulemusedSalvestamiseks = tulemusedSalvestamiseks;
    }

    public BorderPane loomeL(){
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
        bp2.setRight(väljuJaSalvesta());

        return lõpp;
    }

    public Button uuestiNupp() {
        nuppUuesti = new Button("Proovin uuesti");

        nuppUuesti.setOnMouseClicked(event -> {
            try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("uuesti.dat"))){
                dos.writeInt(tulemusedSalvestamiseks.size());
                for(String key:tulemusedSalvestamiseks.keySet()){
                    dos.writeUTF(key);
                    dos.writeInt(tulemusedSalvestamiseks.get(key));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            m = new Müüja(pealava, juur);
            juur.getChildren().add(m.getKirjelduseAken());
            m.setJuur(juur);
            m.getKirjelduseAken().setVisible(true);
            lõpp.setVisible(false);
        });

        return nuppUuesti;
    }


    public Button väljuJaSalvesta(){
        nuppVälju = new Button("Välju ja salvesta");

        File f = new File("uuesti.dat");
        if(f.isFile()){
            f.delete();
        }

        nuppVälju.setOnMouseClicked(event -> {
            try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("SalvestatudTulemused.dat"))){
                dos.writeInt(tulemusedSalvestamiseks.size());
                for(String key:tulemusedSalvestamiseks.keySet()){
                    dos.writeUTF(key);
                    dos.writeInt(tulemusedSalvestamiseks.get(key));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Platform.exit();

        });

        return nuppVälju;
    }


    public BorderPane getLõpp(){
        return lõpp;
    }


    public void setTulemused(String tulemused) {
        this.tulemused = tulemused;
    }

    public void setJuur(Group juur) {
        this.juur = juur;
    }

}
