package cahierIG;

import javafx.beans.binding.BooleanExpression;
import javafx.scene.layout.Pane;

public abstract class NodeIG {
    String identifiant;

    public NodeIG() {

    }

    abstract Boolean estTexte();
    abstract Boolean estImage();
    abstract Boolean estGPS();

}
