package cahierIG;

import com.google.gson.annotations.Expose;
import javafx.scene.control.Label;

public class NodeTexteIG extends NodeIG{
    @Expose
    String texte;


    public NodeTexteIG()
    {
        super();
    }

    public NodeTexteIG(String texte)
    {
        super();
        this.texte = texte;
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
    }


    @Override
    public Boolean estTexte() {
        return true;
    }

    @Override
    public Boolean estImage() {
        return false;
    }

    @Override
    public Boolean estGPS() {
        return false;
    }
}
