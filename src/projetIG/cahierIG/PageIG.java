package cahierIG;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public abstract class PageIG implements Iterable<NodeIG>{

    String titre;
    String identifiant;

    HashMap<String, NodeIG> nodes;

    public PageIG () {
        new HashMap<String,NodeIG>();
        this.identifiant = FabriqueIdentifiant.getInstance().getIdentifiantPage();
    }


    public String getIdentifiantPage()
    {
        return identifiant;
    }

    /**
     * Récupère une node de la page
     * @param id récupère la node portant cet identifiant
     * @return une node
     */
    public NodeIG getNodeIG(String id)
    {
        return null;
    }

    /**
     * Change la node séléctionnée
     * @param id identifiant du node à changer
     * @param node nouvelle node
     */
    public void setNodeIG(String id, NodeIG node)
    {

    }

    /**
     * Supprime une node
     * @param id node à supprimer
     */
    public void supprimerNodeIG(String id)
    {

    }

    public abstract Boolean estPageJour();
    public abstract Boolean estPageDeGarde();


}
