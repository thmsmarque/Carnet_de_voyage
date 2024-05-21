package cahierIG;

import exceptions.CahierException;
import javafx.scene.layout.Pane;
import outils.FabriqueIdentifiant;

import java.util.Date;
import java.util.HashMap;

public abstract class PageIG{

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
        dateDuJour = new DateCahier("01/01/2000");
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
     * Change la node séléctionnée, -1 = null  1 = largeNode 2 = smallNodeLeft 3 = smallNodeRight
     * @param node nouvelle node
     */
    public void setNodeIG(NodeIG nodeIG,int node)
    {

        if(node == 1)
        {
            largeNode = nodeIG;
        }
        if(node == 2)
        {
            smallNodeLeft = nodeIG;
        }
        if(node == 3)
        {
            smallNodeRight = nodeIG;
        }
    }

    public NodeIG getLargeNode()
    {
        return largeNode;
    }

    public NodeIG getSmallNodeLeft()
    {
        return smallNodeLeft;
    }

    public NodeIG getSmallNodeRight()
    {
        return smallNodeRight;
    }

    /**
     * Selectionne une node selon le int, -1 = null  1 = largeNode 2 = smallNodeLeft 3 = smallNodeRight
     * @param node
     */
    public void setNodeSelectionnee(int node)
    {
        if(node == 0)
        {
            nodeSelectionnee = null;
        }
        if(node == 1)
        {
            nodeSelectionnee = largeNode;
        }
        if(node == 2)
        {
            nodeSelectionnee = smallNodeLeft;
        }
        if(node == 3)
        {
            nodeSelectionnee = smallNodeRight;
        }
        System.out.println("Node selected après setNodeSelec : " + nodeSelectionnee);
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

    public NodeIG getNodeSelectionnee()
    {
        return nodeSelectionnee;
    }

    public void deselectionnerNode()
    {
        nodeSelectionnee = null;
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
