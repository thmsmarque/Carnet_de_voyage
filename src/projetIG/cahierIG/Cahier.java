package cahierIG;

import exceptions.CahierException;

import java.util.*;

public class Cahier implements Iterable<PageIG> {

    String auteur;
    ArrayList<String> participants;
    HashMap<DateCahier,PageIG> pages;
    DateCahier courante;
    DateCahier maximum, minimum;

    PageDeGardeIG pdg;

    public Cahier()
    {
        participants = new ArrayList<>();
        pages = new HashMap<DateCahier, PageIG>();
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
    public void ajouterPage(DateCahier dateCahier, String titre) throws CahierException
    {
        if(pages.containsKey(dateCahier))
        {
            throw new CahierException("Problème! Cette date a déjà été attribuée à une page");
        }
        PageJourIG page = new PageJourIG(dateCahier, titre);
        pages.put(dateCahier,page);
        courante = dateCahier;
        if(minimum==null)
        {
            minimum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }
        if(maximum==null)
        {
            maximum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }
        if(dateCahier.before(minimum))
        {
            minimum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }
        if(dateCahier.after(maximum))
        {
            maximum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }
    }

    /**
     * Retourne la date courante
     * @return la date courante
     */
    public DateCahier getCourante()
    {
        return courante;
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
     * passe à la journée d'après
     */
    public void jourSuivant()
    {
        DateCahier nouvelle = new DateCahier(courante.annee, courante.mois, courante.jour);
        try {
            nouvelle.setDate(nouvelle.jourSuivant());
            while(!pages.containsKey(nouvelle))
            {
                nouvelle.setDate(nouvelle.jourSuivant());
            }
            courante = nouvelle;
        } catch (CahierException e) {
            throw new RuntimeException(e);
        }
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
    public void jourPrecedent()
    {
        DateCahier nouvelle = new DateCahier(courante.annee, courante.mois, courante.jour);
        try {
            nouvelle.setDate(nouvelle.jourPrecedent());
            while(!pages.containsKey(nouvelle))
            {
                nouvelle.setDate(nouvelle.jourPrecedent());
            }
            courante = nouvelle;
        } catch (CahierException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * définit la page comme étant celle passée en param
     * @param dateCahier la page à accéder
     */
    public void changerPage(DateCahier dateCahier)
    {
        courante = dateCahier;
    }

    /**
     * Retourne une PageIG
     * @param dateCahier date de la page à récupérer
     * @return page
     */
    public PageIG getPage(DateCahier dateCahier) throws CahierException
    {
        if(!pages.containsKey(dateCahier))
        {
            throw new CahierException("Problème! Cette page n'existe pas");
        }else
        {
            return pages.get(dateCahier);
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

    /**
     * Retourne la date maximum
     * @return date max
     */
    public DateCahier getMaximum()
    {
        return maximum;
    }

    /**
     * Retourne la date minimum
     * @return date min
     */
    public DateCahier getMinimum()
    {
        return minimum;
    }


    @Override
    public Iterator<PageIG> iterator() {
        return pages.values().iterator();
    }
}
