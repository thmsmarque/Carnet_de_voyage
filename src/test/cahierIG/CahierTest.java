package cahierIG;

import exceptions.CahierException;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class CahierTest {

    @Test
    void genererPDG() {
    }

    @Test
    void ajouterPage() throws CahierException {
        Cahier cahier = new Cahier();
        Date date = new Date(2002, Calendar.AUGUST,27);

        CahierException thrown = assertThrows(CahierException.class, () -> {
            cahier.ajouterPage(date,"Ma naissance");
            cahier.ajouterPage(date,"Ma naissance2");
        });

        assertEquals("Problème! Cette date a déjà été attribuée à une page", thrown.getMessage());
        assertEquals(1,cahier.nombreDeJours());
        assertEquals("Ma naissance",cahier.getPage(date).getTitre());

    }

    @Test
    void getPages() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new Date(2002, Calendar.AUGUST,27);
        DateCahier date2 = new Date(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date,"Ma naissance");
        cahier.ajouterPage(date2,"Ma naissance2");

        assertEquals("Ma naissance",cahier.getPage(date).getTitre());
        assertEquals("Ma naissance2",cahier.getPage(date2).getTitre());

    }

    @Test
    void pageSuivante() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);
        DateCahier date2 = new Date(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date,"Ma naissance");
        assertEquals(cahier.getPage(date),cahier.getCourante());
        cahier.ajouterPage(date2,"Ma naissance2");
        assertEquals(cahier.getPage(date2),cahier.getCourante());
        cahier.changerPage(date);
        cahier.pageSuivante();
        assertEquals(cahier.getPage(date2),cahier.getCourante());


    }

    @Test
    void pagePrecedente() throws CahierException {
        Cahier cahier = new Cahier();
        Date date = new Date(2002, Calendar.AUGUST,27);
        Date date2 = new Date(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date,"Ma naissance");
        cahier.ajouterPage(date2,"Ma naissance2");
    }

    @Test
    void changerPage() {
    }

    @Test
    void ajouterParticipant() {
    }

    @Test
    void trierPages() throws CahierException {
        Cahier cahier = new Cahier();
        Date date = new Date(2002, Calendar.AUGUST,27);
        Date date2 = new Date(2003, Calendar.AUGUST,27);
        Date date3 = new Date(2004, Calendar.AUGUST,27);
        Date date4 = new Date(2005, Calendar.AUGUST,27);

        cahier.ajouterPage(date,"Ma naissance");
        cahier.ajouterPage(date2,"Ma naissance2");
        cahier.ajouterPage(date3,"Ma naissance3");
        cahier.ajouterPage(date4,"Ma naissance4");

        Collection<PageIG> pages = cahier.getPages();

        System.out.println(cahier.toString());

        /*assertEquals(cahier.getPage(date4), iterator.next());
        assertEquals(cahier.getPage(date3),iterator.next());
        assertEquals(cahier.getPage(date2),iterator.next());
        assertEquals(cahier.getPage(date),iterator.next());*/
    }

    @Test
    void nombreDeJours() {
    }

    @Test
    void getPage() {
    }
}