import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Teated {

    private List<String> teadeteList = new ArrayList<>();

    public Teated(List<String> teadeteList) {
        this.teadeteList = teadeteList;
    }

    public void lisaTeade(String teade){
        teadeteList.add(teade);
    }

    public List<String> getTeadeteList() {
        return teadeteList;
    }

    public void setTeadeteList(List<String> teadeteList) {
        this.teadeteList = teadeteList;
    }
}
