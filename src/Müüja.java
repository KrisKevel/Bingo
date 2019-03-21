import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Müüja {


    public String tervitus(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Tere! Kas soovite mängida bingot? (jah/ei)");
        String vastus = scan.nextLine();

        if(vastus.equals("jah")){
            System.out.println("Mis on teie nimi?");
            vastus = scan.nextLine();

            return vastus;
        }

        System.out.println(":("); //ei teadnud, mida siia panna :D
        return "";
    }

}
