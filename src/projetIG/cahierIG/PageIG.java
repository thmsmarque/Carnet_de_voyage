package cahierIG;

import com.google.gson.annotations.Expose;
import exceptions.CahierException;
import javafx.scene.layout.Pane;
import outils.FabriqueIdentifiant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class PageIG implements Iterable<NodeIG>{


    @Expose
    private DateCahier dateDuJour = null;
    @Expose
    String titre;
    @Expose
    String identifiant;
    @Expose
    NodeIG nodeSelectionnee;

    @Expose
    NodeIG smallNodeLeftBottom;
    @Expose
    NodeIG smallNodeRightBottom;
    @Expose
    NodeIG smallNodeRightTop;
    @Expose
    NodeIG smallNodeLeftTop;

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

    public void setDate(String date)
    {
        dateDuJour = new DateCahier(date);
    }

    public void setDate(DateCahier date)
    {
        dateDuJour = date;
    }


    public String getIdentifiantPage()
    {
        return identifiant;
    }

    /**
     * Récupère une node de la page
     * @param node récupère la node portant cet identifiant
     * @return une node
     */
    public NodeIG getNodeIG(int node)
    {
        if(node == 2)
        {
            return smallNodeLeftBottom;
        }
        if(node == 3)
        {
            return smallNodeRightBottom;
        }
        if(node == 4)
        {
            return smallNodeLeftTop;
        }
        if(node == 5)
        {
           return smallNodeRightTop;
        }
        else return null;
    }

    /**
     * Change la node séléctionnée, 2 = smallNodeLeftBottom 3 = smallNodeRightBottom 4 = smallNodeLeftTop 5 = smallNodeRightTop
     * @param node nouvelle node
     */
    public void setNodeIG(NodeIG nodeIG,int node)
    {

        if(node == 2)
        {
            smallNodeLeftBottom = nodeIG;
        }
        if(node == 3)
        {
            smallNodeRightBottom = nodeIG;
        }
        if(node == 4)
        {
            smallNodeLeftTop = nodeIG;
        }
        if(node == 5)
        {
            smallNodeRightTop = nodeIG;
        }
    }




    public NodeIG getSmallNodeLeftBottom()
    {
        return smallNodeLeftBottom;
    }

    public NodeIG getSmallNodeRightBottom()
    {
        return smallNodeRightBottom;
    }

    public NodeIG getSmallNodeRightTop()
    {
        return smallNodeRightTop;
    }

    public NodeIG getSmallNodeLeftTop()
    {
        return smallNodeLeftTop;
    }

    /**
     * Selectionne une node selon le int, -1 = null  2 = smallNodeLeftBottom 3 = smallNodeRightBottom 4 = smallNodeLeftTop 5 = smallNodeRightTop
     * @param node
     */
    public void setNodeSelectionnee(int node)
    {
        if(node == 0)
        {
            nodeSelectionnee = null;
        }
        if(node == 2)
        {
            nodeSelectionnee = smallNodeLeftBottom;
        }
        if(node == 3)
        {
            nodeSelectionnee = smallNodeRightBottom;
        }
        if(node == 4)
        {
            nodeSelectionnee = smallNodeLeftTop;
        }
        if(node == 5)
        {
            nodeSelectionnee = smallNodeRightTop;
        }
        //System.out.println("Node selected après setNodeSelec : " + nodeSelectionnee);
    }



    /**
     * Supprime une node selon le int 2 = smallNodeLeftBottom 3 = smallNodeRightBottom 4 = smallNodeLeftTop 5 = smallNodeRightTop
     * @param node node à supprimer
     */
    public void supprimerNodeIG(int node)
    {
        if(node == 2)
        {
            smallNodeLeftBottom = null;
        }
        if(node == 3)
        {
            smallNodeRightBottom = null;
        }
        if(node == 4)
        {
            smallNodeLeftTop = null;
        }
        if(node == 5)
        {
            smallNodeRightTop = null;
        }
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


    public void setTitre(String titre) throws CahierException
    {
        if(titre == null || titre.length()>22)
        {
            throw new CahierException("Le titre ne peut pas être vide ou faire plus de 22 caractères!");

        }
        this.titre = titre;
    }
    /**
     * Renvoie une chaîne de caractère
     * @return
     */
    public String toString()
    {
        return titre + " " + dateDuJour.toString();
    }

    public DateCahier getDate()
    {
        return dateDuJour;
    }


    @Override
    public Iterator<NodeIG> iterator() {
        ArrayList<NodeIG> nodes = new ArrayList<>();
        nodes.add(smallNodeLeftBottom);
        nodes.add(smallNodeRightBottom);
        nodes.add(smallNodeLeftTop);
        nodes.add(smallNodeRightTop);

        return nodes.iterator();
    }
}
