package cahierIG;

public class NodeTexteIG extends NodeIG{

    String texte;

    public NodeTexteIG()
    {
        super();
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
