package vues;

import cahierIG.Cahier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurPageDuJour extends PanneauDeControle implements EventHandler<ActionEvent> {


    public EcouteurPageDuJour(Cahier c) {
        super(c);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Le bouton du jour a été pressé");
    }
}
