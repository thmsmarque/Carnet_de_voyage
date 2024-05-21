package cahierIG;

import exceptions.CahierException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateCahier extends Date {

    int annee = 1,mois = 1,jour = 1;

    public DateCahier(int annee, int mois, int jour)
    {
        super(annee,mois-1,jour);
        try {
            this.setAnnee(annee);
            this.setMois(mois);
            this.setJour(jour);
        } catch (CahierException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructeur dont la date est au format DD/MM/YYYY
     * @param nouvDate la chaîne de caractère qui correspond à la date
     */
    public DateCahier(String nouvDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(nouvDate, formatter);

         jour = date.getDayOfMonth();
         mois = date.getMonthValue();
         annee = date.getYear();
    }


    public DateCahier()
    {
        super();
    }

    public String toString()
    {
        return jour+"/"+mois+"/"+annee;
    }

    /**
     * Retourne l'année
     * @return une année
     */
    public int getAnnee()
    {
        return annee;
    }

    /**
     * Retourne le mois
     * @return un mois
     */
    public int getMois()
    {
        return mois;
    }

    public String getMoisString()
    {
        switch(getMois())
        {
            case 1: return "Janvier";
            case 2: return "Février";
            case 3: return "Mars";
            case 4: return "Avril";
            case 5: return "Mai";
            case 6: return "Juin";
            case 7: return "Juillet";
            case 8: return "Août";
            case 9: return "Septembre";
            case 10: return "Octobre";
            case 11: return "Novembre";
            case 12: return "Décembre";
        }
        return null;
    }

    /**
     * Retourne le jour du mois
     * @return jour du mois
     */
    public int getJourDuMois()
    {
        return jour;
    }


    /**
     * Retourne le jour de la semaine representée par cette date
     * @return
     */
    public int getJourDeLaSemaine()
    {
        return this.getDay()+1;
    }

    public String getJourDeLaSemaineString()
    {
        switch(getJourDeLaSemaine())
        {
            case 1: return "Lundi";
            case 2: return "Mardi";
            case 3: return "Mercredi";
            case 4: return "Jeudi";
            case 5: return "Vendredi";
            case 6: return "Samedi";
            case 7: return "Dimanche";
        }
        return null;
    }

    /**
     * Met à jour l'année
     * @param annee nouvelle année
     * @throws CahierException l'année doit être comprise entre 1901 et 2099
     */
    public void setAnnee(int annee) throws CahierException
    {
        if(annee<1900 || annee>2100)
        {
            throw new CahierException("L'année doit se trouver entre 1900 et 2100. Année donnée : "+annee);
        }
        this.annee = annee;
        this.setYear(annee);
    }

    /**
     * Met à jour le mois
     * @param mois nouveau mois
     * @throws CahierException mois doit être compris entre 1 et 12
     */
    public void setMois(int mois) throws CahierException
    {
        if(mois<=0 || mois>=13)
        {
            throw new CahierException("Le mois doit se trouver entre 1(janvier) et 12(décembre). Mois donné : "+mois);
        }
        this.mois = mois;
        this.setMonth(mois-1);
    }

    /**
     * Met à jour le jour
     * @param jour nouveau jour
     * @throws CahierException jour doit être compris entre 1 et 31
     */
    public void setJour(int jour) throws CahierException
    {
        if(jour<=0 || jour >=32)
        {
            throw new CahierException("Le jour doit se trouver entre 1 et 31. Jour donné : "+jour);
        }
        this.jour = jour;
        this.setDate(jour);
    }

    /**
     * Met à jour la date totale
     * @param date nouvelle date
     * @throws CahierException
     */
    public void setDate(DateCahier date) throws CahierException
    {
        this.setJour(date.getJourDuMois());
        this.setMois(date.getMois());
        this.setAnnee(date.getAnnee());
    }

    /**
     * Met à jour la date totale
     * @param annee nouvelle annee
     * @param mois nouveau mois
     * @param jour nouveau jour
     * @throws CahierException
     */
    public void setDate(int annee, int mois, int jour) throws CahierException
    {
        this.setJour(jour);
        this.setMois(mois);
        this.setAnnee(annee);
    }

    /**
     * Renvoi le jour suivant
     * @return jour suivant
     */
    public DateCahier jourSuivant() throws CahierException
    {
        DateCahier jourSuivant = new DateCahier();
        jourSuivant.setDate(this);

        if(mois == 2 && jour == 28)//Si nous somme le dernier jour de février
        {
            jourSuivant.setJour(1);
            jourSuivant.setMois(3);
        }else if((mois == 1 || mois == 3 || mois ==5 || mois == 7 || mois == 8 || mois == 10) && jour == 31)
        {
            //Si le mois finit en 31  (sauf décembre)
            jourSuivant.setJour(1);
            jourSuivant.setMois(mois+1);
        }else if((mois == 4 || mois == 6 || mois == 9 || mois == 11) && jour == 30)
        {
            //Si le mois finit en 30
            jourSuivant.setJour(1);
            jourSuivant.setMois(mois+1);
        }else if(mois == 12 && jour == 31)
        {
            //Si c'est le dernier jour de l'année
            jourSuivant.setJour(1);
            jourSuivant.setMois(1);
            jourSuivant.setAnnee(annee+1);
        }else
        {
            //Jour banal
            jourSuivant.setJour(jour+1);
        }



        return jourSuivant;
    }

    /**
     * Renvoi le jour précédent
     * @return le jour précédent
     */
    public DateCahier jourPrecedent() throws CahierException
    {
        DateCahier jourSuivant = new DateCahier();
        jourSuivant.setDate(this);

        if(mois == 3 && jour == 1)//Si nous somme le dernier jour de février
        {
            jourSuivant.setJour(28);
            jourSuivant.setMois(2);
        }else if((mois == 2 || mois == 4 || mois ==6 || mois == 8 || mois == 9 || mois == 11) && jour == 1)
        {
            jourSuivant.setJour(31);
            jourSuivant.setMois(mois-1);
        }else if((mois == 5 || mois == 7 || mois == 10 || mois == 12) && jour == 1)
        {
            //Si le mois finit en 30
            jourSuivant.setJour(30);
            jourSuivant.setMois(mois-1);
        }else if(mois == 1 && jour == 1)
        {
            //Si c'est le dernier jour de l'année
            jourSuivant.setJour(31);
            jourSuivant.setMois(12);
            jourSuivant.setAnnee(annee-1);
        }else
        {
            jourSuivant.setJour(jour-1);
        }



        return jourSuivant;
    }


    public boolean equalsDate(DateCahier date)
    {
        if(this.jour == date.jour && this.mois == date.mois && this.annee == date.annee)
            return true;
        else
            return false;
    }

    /**
     * Test si la date passée en param est la même
     * @param o la date à tester
     * @return vrai si identiques faux sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateCahier that = (DateCahier) o;
        return jour == that.jour && mois == that.mois && annee == that.annee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jour, mois, annee);
    }

    /**
     * Test si la date passée en paramètre est avant la date
     * @param date date à tester
     * @return
     */
    public boolean avant(DateCahier date)
    {
        if(date.annee > annee)
            return false;
        else if(date.annee < annee)
        {
            return true;
        }else if(date.mois < mois)
        {
            return true;
        }else if(date.mois > mois)
        {
            return false;
        }else if(date.jour > jour)
        {
            return false;
        }else if(date.jour < jour)
        {
            return true;
        }else
            return false;
    }

    /**
     * Test si la date passée en paramètre est apres la date
     * @param date date à tester
     * @return
     */
    public boolean apres(DateCahier date)
    {
        if(date.annee > annee)
            return true;
        else if(date.annee < annee)
        {
            return false;
        }else if(date.mois < mois)
        {
            return false;
        }else if(date.mois > mois)
        {
            return true;
        }else if(date.jour > jour)
        {
            return true;
        }else if(date.jour < jour)
        {
            return false;
        }else
            return false;
    }




    public String format2()
    {
       return getJourDeLaSemaineString()+" "+getJourDuMois()+" "+getAnnee();
    }

}
