package cahierIG;

import com.google.gson.annotations.Expose;
import exceptions.CahierException;


import javax.lang.model.type.ArrayType;
import java.util.*;

public class Cahier extends SujetObserve implements Iterable<PageIG>{

    @Expose
    String auteur;
    @Expose
    ArrayList<String> participants;
    @Expose
    HashMap<DateCahier,PageIG> pages;
    @Expose
    DateCahier courante;
    @Expose
    DateCahier maximum;
    @Expose
    DateCahier minimum;

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
        if(this.estDejaDansCahier(dateCahier))
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
        if(minimum.avant(dateCahier))
        {
            minimum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }
        if(maximum.apres(dateCahier))
        {
            maximum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }

        System.out.println("Une nouvelle page a été ajoutée! => " + page.toString());
        System.out.println("Minimum : "+minimum.toString() + " -- Maximum : " + maximum.toString());
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
     * Supprimer une page du cahier à partir d'une date
     * @param dateCahier la date à supprimer
     */
    public void supprimerPage(DateCahier dateCahier)
    {
        if(this.estDejaDansCahier(dateCahier))
        {
            pages.remove(dateCahier);
        }
        courante = null;
        if(dateCahier.equals(minimum))
        {
            if(!pages.isEmpty())
            {
                minimum = nouvelleMinimum();
            }
        }
        if(dateCahier.equals(maximum))
        {
            if(!pages.isEmpty())
            {
                maximum = nouvelleMaximum();
            }
        }
    }

    public DateCahier nouvelleMinimum()
    {
        DateCahier dateCahier = maximum;

        for(DateCahier date : pages.keySet())
        {
            if(date.avant(dateCahier))
            {
                dateCahier = date;
            }
        }
        return dateCahier;
    }

    public DateCahier nouvelleMaximum()
    {
        DateCahier dateCahier = minimum;
        for(DateCahier date : pages.keySet())
        {
            if(date.apres(dateCahier))
            {
                dateCahier = date;
            }
        }
        return dateCahier;
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
        System.out.println("Tentative de passage au jour suivant...\n");

        DateCahier nouvelle = new DateCahier(courante.annee, courante.mois, courante.jour);
        if(nouvelle.before(maximum)) {
            //System.out.println("Le jour suivant est avant le maximum...\n");
            try {
                do {
                    nouvelle.setDate(nouvelle.jourSuivant());
                } while (!this.estDejaDansCahier(nouvelle) && nouvelle.before(maximum));
                //System.out.println("Passage au jour suivant : " + nouvelle.toString());
                courante = nouvelle;
            } catch (CahierException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Cette méthode test si la date est déjà présente dans le cahier
     * @param date date à tester
     * @return vrai si déjà présent sinon faux
     */
    public boolean estDejaDansCahier(DateCahier date)
    {
        for(PageIG page : pages.values())
        {
            if(page.getDateDuJour().equalsDate(date))
            {
                //System.out.println(date.toString() + " cette date est présente dans le cahier");
                return true;
            }
        }
        //System.out.println(date.toString() + " cette date n'est pas présente dans le cahier");
        return false;
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
        //System.out.println("Tentative de passage au jour précédent...\n");
        DateCahier nouvelle = new DateCahier(courante.annee, courante.mois, courante.jour);
        if(nouvelle.after(minimum)) {
            //System.out.println("Le jour précédent est après le minimum...\n");

            try {
                do {
                    nouvelle.setDate(nouvelle.jourPrecedent());
                } while (!this.estDejaDansCahier(nouvelle) && nouvelle.after(minimum));
                //System.out.println("Passage au jour précédent : " + nouvelle.toString());
                courante = nouvelle;
            } catch (CahierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * définit la page comme étant celle passée en param
     * @param dateCahier la page à accéder
     */
    public void changerPage(DateCahier dateCahier) throws CahierException
    {
        if(this.estDejaDansCahier(dateCahier)) {
            //System.out.println("Nouvelle page courante : " + getPage(dateCahier));
            courante = dateCahier;
        }else
            throw new CahierException("Ce jour n'existe pas");
    }

    /**
     * Retourne une PageIG
     * @param dateCahier date de la page à récupérer
     * @return page
     */
    public PageIG getPage(DateCahier dateCahier) throws CahierException
    {
        System.out.println("Date à récupérer :"+ dateCahier.toString());
        if(!this.estDejaDansCahier(dateCahier))
        {
            throw new CahierException("Problème! Cette page n'existe pas");
        }else
        {
            PageIG page = pages.get(dateCahier);
            if(page==null)
            {
                System.out.println("Page n'existe pas dans getPage(DateCahier dateCahier)\n");
            }else {
                //System.out.println("Page renvoyée : " + page.toString());
            }
            return page;
        }
    }

    public PageJourIG getPageCourante()
    {
        //System.out.println(courante.toString());
        PageJourIG page = (PageJourIG)pages.get(courante);
        if(page == null)
        {
            //System.out.println("Page n'existe pas");
            return null;
        }else
            return page;
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

    /**
     * Définit une nouvelle date courante
     * @param date nouvelle date
     */
    public void setCourante(DateCahier date)
    {
        this.courante = date;
    }

    /**
     * Renvoi sous forme de String les jours existants et leurs titres
     * @return une chaîne de caractère
     */
    public String toString()
    {
        String res = "Les pages du cahier:\n";
        for(PageIG page : pages.values())
        {
            res += page.toString() + "\n";
        }

        return res;
    }

    public ArrayList<String> getParticipants()
    {
        return participants;
    }


    @Override
    public Iterator<PageIG> iterator() {
        return pages.values().iterator();
    }


}
