package vues;

import cahierIG.Cahier;
import cahierIG.NodeIG;
import javafx.scene.layout.Pane;

public class VueNodeIG extends Pane implements Observateur{

    Cahier cahier;
    NodeIG node;
    public VueNodeIG(Cahier c, NodeIG n)
    {
        cahier = c;
        node = n;
    }

    @Override
    public void reagir() {

    }
}
