package cahierIG;

import javafx.beans.binding.BooleanExpression;

public abstract class NodeIG {
    String identifiant;

    public NodeIG() {

    }

    abstract Boolean estTexte();
    abstract Boolean estImage();
    abstract Boolean estGPS();

}
