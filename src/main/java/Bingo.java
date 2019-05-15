import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


//kirjeldust võid muuta, kui on mingi idee olemas, panin täitsa midagi random :D
//TODO: kuidas näitame võidunumbreid mängu käigus?
//TODO: mängija pileti näitamine
//TODO: klassis Peaaken väljastada andmeid aknasse (praegu kõik läheb konsooli pmst)
//
//mingi disaini moodi asja võiks ka lisada, kui aega ja jaksu on :D aga ei usu,
//et keegi väga pahandab, kui seda ei ole


public class Bingo extends Application {

    public Group getGroup(Stage pealava) throws InterruptedException {
        Group juur = new Group();

        Müüja m = new Müüja(pealava, juur);

        juur.getChildren().addAll(m.getKirjelduseAken());
        return juur;
    }


    public void start(Stage pealava) throws InterruptedException {
        Group juur = getGroup(pealava);

        Scene stseen = new Scene(juur, 500, 500);
        pealava.setScene(stseen);
        pealava.setTitle("Bingo");
        pealava.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
