package vues;

import cahierIG.Cahier;

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
     * @param id page à regarder
     */
    public void choisirPage(String id)
    {
        cahier.changerPage(id);
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
    public void ajouterPage(Date date)
    {
        cahier.ajouterPage(date);
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
