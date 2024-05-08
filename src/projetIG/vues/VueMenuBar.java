package vues;

import cahierIG.Cahier;
import javafx.scene.control.MenuBar;

public class VueMenuBar extends MenuBar implements Observateur{

    Cahier cahier;

    public VueMenuBar(Cahier cahier) {
        this.cahier = cahier;
    }

    @Override
    public void reagir() {

    }
}
