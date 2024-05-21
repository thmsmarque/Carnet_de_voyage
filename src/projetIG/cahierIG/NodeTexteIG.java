package cahierIG;

import javafx.scene.control.Label;

public class NodeTexteIG extends NodeIG{

    String texte;
    Label texteIG;

    public NodeTexteIG()
    {
        super();
        texteIG = new Label();
    }

    public NodeTexteIG(String texte)
    {
        super();
        this.texte = texte;
        texteIG = new Label(texte);
        this.getChildren().add(texteIG);
    }

    /**
     * Retourne le texte de la node
     * @return String
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Met à jour le texte
     * @param texte nouveau texte
     */
    public void setTexte(String texte) {
        this.texte = texte;
        texteIG.setText(texte);
    }


    @Override
    Boolean estTexte() {
        return true;
    }

    @Override
    Boolean estImage() {
        return false;
    }

    @Override
    Boolean estGPS() {
        return false;
    }
}
