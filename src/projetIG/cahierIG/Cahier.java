package cahierIG;

import exceptions.CahierException;

import java.util.*;

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
    PageDeGardeIG genererPDG()
    {
        PageDeGardeIG pdg = new PageDeGardeIG();
        return pdg;
    }

    /**
     * Ajouter une nouvelle page si elle n'a pas déjà été ajoutée
     */
    public void ajouterPage(Date date, String titre) throws CahierException
    {
        if(pages.containsKey(date))
        {
            throw new CahierException("Problème! Cette date a déjà été attribuée à une page");
        }
        PageJourIG page = new PageJourIG(date, titre);
        pages.put(date,page);
        courante = page;
        trierPages();
    }

    /**
     * Retourne la page courante
     * @return la page courante
     */
    public PageIG getCourante()
    {
        return courante;
    }

    /** 
     * Trie les pages dans un ordre chronologique
     */
    private void trierPages()
    {
        Map<Date, PageIG> map = new TreeMap<Date, PageIG>(pages);
        HashMap<Date,PageIG> sortedHashmap = new HashMap<>();
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashmap.put((Date)entry.getKey(),(PageIG)entry.getValue());
        }
        pages = sortedHashmap;
    }

    /**
     * Retourne les pages du cahier déjà présentes
     * @return les pages
     */
    public Collection<PageIG> getPages()
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
     * Retourne le nombre de jours déjà entrés
     * @return nombre de PageIG
     */
    public int nombreDeJours()
    {
        return pages.size();
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
     * Retourne une PageIG
     * @param date date de la page à récupérer
     * @return page
     */
    public PageIG getPage(Date date) throws CahierException
    {
        if(!pages.containsKey(date))
        {
            throw new CahierException("Problème! Cette page n'existe pas");
        }else
        {
            return pages.get(date);
        }
    }

    /**
     * Ajouter un participant à la liste
     * @param nom nom du participant à ajouter
     */
    public void ajouterParticipant(String nom)
    {
        participants.add(nom);
    }

    /**
     * Montre la suite des jours dans l'ordre chronologique
     * @return
     */
    public String toString()
    {
        String res = "";
        for(PageIG page : pages.values())
        {
            res += page.toString() + "\n";
        }
        return res;
    }


    @Override
    public Iterator<PageIG> iterator() {
        return pages.values().iterator();
    }
}
