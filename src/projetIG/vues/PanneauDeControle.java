package vues;

import cahierIG.Cahier;
import exceptions.CahierException;

import java.util.Date;

public class PanneauDeControle {

    Cahier cahier;
    public PanneauDeControle(Cahier c)
    {
        cahier = c;
    }

    /**
     * Passe la page courante à la page suivante
     */
    public void pageSuivante()
    {
        cahier.pageSuivante();
    }

    /**
     * Passe la page courante du cahier à la page précédente
     */
    public void pagePrecedente()
    {
        cahier.pagePrecedente();
    }

    /**
     * Passe la page courante à celle passée en paramètre
     * @param date page à regarder
     */
    public void choisirPage(Date date)
    {
        cahier.changerPage(date);
    }

    /**
     * Change le style du cahier
     */
    public void changerStyle()
    {

    }

    /**
     * Ajoute la node à l'emplacement séléctionné
     * @param type type de la node à ajouter
     */
    public void ajouterNode(String type)
    {

    }

    /**
     * Supprime la node séléctionnée
     */
    public void suppprimerNode()
    {

    }

    /**
     * Si elle n'existe pas, ajoute une nouvelle date
     */
    public void ajouterPage(Date date, String titre) throws CahierException {
        cahier.ajouterPage(date,titre);
    }

    /**
     * Ajoute un nouveau participant
     * @param nom nom du participant à ajouter
     */
    public void ajouterParticipant(String nom)
    {
        cahier.ajouterParticipant(nom);
    }

}
