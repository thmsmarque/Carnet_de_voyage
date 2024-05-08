package vues;

import cahierIG.Cahier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurBoutonDernierePage extends PanneauDeControle implements EventHandler<ActionEvent> {

    Cahier cahier;

    public EcouteurBoutonDernierePage(Cahier c) {
        super(c);
        cahier = c;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
