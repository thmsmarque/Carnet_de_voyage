package cahierIG;

import exceptions.CahierException;
import outils.FabriqueIdentifiant;

import java.util.Date;
import java.util.HashMap;

public abstract class PageIG implements Iterable<NodeIG>{

    private DateCahier dateDuJour = null;
    String titre;
    String identifiant;
    NodeIG nodeSelectionnee;

    NodeIG largeNode;
    NodeIG smallNodeLeft;
    NodeIG smallNodeRight;

    public PageIG()
    {
        new HashMap<String, NodeIG>();
        dateDuJour = new DateCahier();
    }

    public PageIG (DateCahier dateDuJour, String titre) {

            this.dateDuJour = dateDuJour;

        this.titre = titre;
    }

    public PageIG (DateCahier dateDuJour) {
        new HashMap<String,NodeIG>();
        this.dateDuJour = dateDuJour;
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
     * @param node nouvelle node
     */
    public void setNodeIG(NodeIG node)
    {
        if(nodeSelectionnee!=null)
        {
            nodeSelectionnee = node;
        }
    }



    /**
     * Retourne la date du jour de cette page
     * @return date du jour
     */
    public DateCahier getDateDuJour()
    {
        return dateDuJour;
    }

    /**
     * Supprime une node
     * @param id node à supprimer
     */
    public void supprimerNodeIG(String id)
    {

    }

    /**
     * Retourne le titre de la journée
     * @return le titre de la journée
     */
    public String getTitre()
    {
        return titre;
    }

    /**
     * Renvoie une chaîne de caractère
     * @return
     */
    public String toString()
    {
        return titre + " " + dateDuJour.getDate() + " " + (dateDuJour.getMonth()+1) + " " + dateDuJour.getYear();
    }

    public abstract Boolean estPageJour();
    public abstract Boolean estPageDeGarde();


}
