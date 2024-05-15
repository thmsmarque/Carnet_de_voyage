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
    HashMap<Date,PageIG> pages;
    PageIG courante;
    PageDeGardeIG pdg;

    public Cahier()
    {
        participants = new ArrayList<>();
        pages = new HashMap<Date, PageIG>();
        pdg = genererPDG();
    }

    /**
     * Génère une page de garde
     */
    void PageDeGardeIG genererPDG()
    {
        PageDeGardeIG pdg = new PageDeGardeIG();
        return pdg;
    }

    /**
     * Ajouter une nouvelle page si elle n'a pas déjà été ajoutée
     */
    public void ajouterPage(Date date) throws CahierException
    {
        PageJourIG page = new PageJourIG(date)
        pages.putIfAbsent(date,page)
        trierPages();
    }

    /** 
     * Trie les pages dans un ordre chronologique
     */
    private void trierPages()
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
        return pages.values();
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
     * @param date la page à accéder
     */
    public void changerPage(Date date)
    {
        courante = pages.get(date);
    }

    /**
     * Ajouter un participant à la liste
     * @param nom nom du participant à ajouter
     */
    public void ajouterParticipant(String nom)
    {
        participants.add(nom);
    }


    @Override
    public Iterator<PageIG> iterator() {
        return pages.values().iterator();
    }
}
