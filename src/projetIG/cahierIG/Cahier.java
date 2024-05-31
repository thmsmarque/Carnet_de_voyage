package cahierIG;

import com.google.gson.annotations.Expose;
import exceptions.CahierException;
import javafx.fxml.FXMLLoader;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;


import javax.lang.model.type.ArrayType;
import java.net.URL;
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
    @Expose
    String nomVoyage;



    public Cahier()
    {
        participants = new ArrayList<>();
        pages = new HashMap<DateCahier, PageIG>();
        nomVoyage = "Carnet de voyage";
    }



    /**
     * Ajouter une nouvelle page si elle n'a pas déjà été ajoutée
     */
    public void ajouterPage(DateCahier dateCahier, String titre) throws CahierException
    {
        System.out.println("Création d'une nouvelle page au titre de " + titre + " et à la date " + dateCahier.toString());
        if(this.estDejaDansCahier(dateCahier))
        {
            throw new CahierException("Problème! Cette date a déjà été attribuée à une page");
        }
        PageIG page = new PageIG(new DateCahier(dateCahier.toString()), titre);
        System.out.println("La date de la page nouvellement crée : " +page.getDate().toString());
        pages.put(new DateCahier(dateCahier.toString()),page);
        courante = new DateCahier(dateCahier.toString());

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
            System.out.println("Est avant le minimum");
            minimum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }
        if(maximum.apres(dateCahier))
        {
            System.out.println("Est après le maximum");
            maximum = new DateCahier(dateCahier.annee, dateCahier.mois, dateCahier.jour);
        }

        System.out.println("Une nouvelle page a été ajoutée! => " + page.toString());
        System.out.println("Minimum : "+minimum.toString() + " -- Maximum : " + maximum.toString());
    }

    public void ajouterPage(PageIG page) throws CahierException
    {
        if(this.estDejaDansCahier(page.getDate()))
        {
            throw new CahierException("Problème! Cette date a déjà été attribuée à une page");
        }
        pages.put(page.getDate(),page);

        if(minimum==null)
        {
            minimum = new DateCahier(page.getDate().annee, page.getDate().mois, page.getDate().jour);
        }
        if(maximum==null)
        {
            maximum = new DateCahier(page.getDate().annee, page.getDate().mois, page.getDate().jour);
        }
        if(minimum.avant(page.getDate()))
        {
            minimum = new DateCahier(page.getDate().annee, page.getDate().mois, page.getDate().jour);
        }
        if(maximum.apres(page.getDate()))
        {
            maximum = new DateCahier(page.getDate().annee, page.getDate().mois, page.getDate().jour);
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

    public void setMinimum(DateCahier date)
    {
        minimum = date;
    }

    public void setMaximum(DateCahier max)
    {
        maximum = max;
    }

    public void setAuteur(String auteur)
    {
        this.auteur = auteur;
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

        DateCahier nouvelle = new DateCahier(courante.toString());
        if(maximum.avant(nouvelle)) {
            System.out.println("Le jour suivant est avant le maximum...\n");
            try {
                do {
                    nouvelle.setDate(nouvelle.jourSuivant());
                    System.out.println("Passage au jour suivant : " + nouvelle+ " Est dans le cahier? "+  this.estDejaDansCahier(nouvelle) + " Est avant le max?" + maximum.avant(nouvelle));
                } while (!this.estDejaDansCahier(nouvelle) && maximum.avant(nouvelle));
                //System.out.println("Passage au jour suivant : " + nouvelle.toString());
                courante = nouvelle;
            } catch (CahierException e) {
                throw new RuntimeException(e);
            }
        }else
        {
            System.out.println(nouvelle.toString() + " est après " + maximum.toString());
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
            if(page.getDate().equalsDate(date))
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
        DateCahier nouvelle = new DateCahier(courante.toString());
        if(minimum.apres(nouvelle)) {
            //System.out.println("Le jour précédent est après le minimum...\n");

            try {
                do {
                    nouvelle.setDate(nouvelle.jourPrecedent());
                } while (!this.estDejaDansCahier(nouvelle) && minimum.apres(nouvelle));
                //System.out.println("Passage au jour précédent : " + nouvelle.toString());
                courante = nouvelle;
            } catch (CahierException e) {
                throw new RuntimeException(e);
            }
        }else
        {
            System.out.println(nouvelle.toString() + " est avant " + minimum.toString());
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
            throw new CahierException("Problème! Cette page " + dateCahier +" n'existe pas");
        }else
        {
            PageIG page = pages.get(dateCahier);
            if(page==null)
            {
                System.out.println("Page n'existe pas dans getPage(DateCahier dateCahier)\n");
            }else {
                System.out.println("Page renvoyée : " + page.toString());
            }
            return page;
        }
    }

    public PageIG getPageCourante()
    {
        //System.out.println(courante.toString());
        PageIG page = (PageIG)pages.get(courante);
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

    public void chargerCahier(Cahier c)
    {
        this.courante = null;
        this.minimum = c.getMinimum();
        this.maximum = c.getMaximum();
        this.participants.clear();
        this.participants.addAll(c.getParticipants());
        this.pages.clear();
        for(PageIG page : c.getPages())
        {
            this.pages.put(page.getDate(), page);
        }
        this.auteur = c.getAuteur();

        System.out.println("Auteur : " +auteur);
        System.out.println("Minimum : " + minimum.toString());
        System.out.println("Maximum : " +maximum.toString());
        System.out.println("Participants : "+participants);
        System.out.println("Pages : " +  pages.toString());


    }

    public String getNomVoyage()
    {
        return nomVoyage;
    }

    public void setNomVoyage(String nomVoyage)
    {
        this.nomVoyage = nomVoyage;
    }

    public ArrayList<String> getParticipants()
    {
        return participants;
    }

    public String getAuteur()
    {
        return auteur;
    }

    @Override
    public Iterator<PageIG> iterator() {
        return pages.values().iterator();
    }


}
