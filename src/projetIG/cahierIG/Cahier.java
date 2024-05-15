package cahierIG;

import exceptions.CahierException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class Cahier implements Iterable<PageIG> {

    String auteur;
    String intervalleTemps;
    ArrayList<String> participants;
    HashMap<String,PageIG> pages;
    PageIG courante;

    public Cahier()
    {
        participants = new ArrayList<>();
        pages = new HashMap<String, PageIG>();
    }

    /**
     * Génère une page de garde
     */
    void genererPDG()
    {

    }

    /**
     * Ajouter une nouvelle page
     */
    public void ajouterPage(Date date) throws CahierException
    {

        for(PageIG page : pages.values())
        {
            if(page.getDateDuJour().equals(date))
            {
                throw new CahierException("Problème : la date renseignée a déjà été attribuée ")
            }
        }


    }

    /**
     * Retourne les pages du cahier déjà présentes
     * @return
     */
    public ArrayList<PageIG> getPages()
    {
        return null;
    }

    /**
     * définit la page courante comme étant la suivante
     */
    public void pageSuivante()
    {

    }

    /**
     * définit la page courante comme étant la précédente
     */
    public void pagePrecedente()
    {

    }

    /**
     * définit la page comme étant celle passée en param
     * @param identifiant la page à accéder
     */
    public void changerPage(String identifiant)
    {

    }

    /**
     * Ajouter un participant à la liste
     * @param nom nom du participant à ajouter
     */
    public void ajouterParticipant(String nom)
    {

    }


    @Override
    public Iterator<PageIG> iterator() {
        return pages.values().iterator();
    }
}
