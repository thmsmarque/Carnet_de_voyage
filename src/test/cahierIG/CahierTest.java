package cahierIG;

import exceptions.CahierException;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class CahierTest {

    @Test
    void ajouterPage() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);

        CahierException thrown = assertThrows(CahierException.class, () -> {
            cahier.ajouterPage(date,"Ma naissance");
            cahier.ajouterPage(date,"Ma naissance2");
        });

        assertEquals("Problème! Cette date a déjà été attribuée à une page", thrown.getMessage());
        assertEquals(1,cahier.nombreDeJours());
        assertEquals("Ma naissance",cahier.getPage(date).getTitre());

    }


    @Test
    void jourSuivant() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);
        DateCahier date2 = new DateCahier(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date2,"Ma naissance2");
        assertEquals(date2,cahier.getCourante());
        cahier.ajouterPage(date,"Ma naissance");
        assertEquals(date,cahier.getCourante());
        cahier.jourSuivant();
        assertEquals(date2,cahier.getCourante());


    }

    @Test
    void jourPrecedent() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);
        DateCahier date2 = new DateCahier(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date,"Ma naissance");
        assertEquals(date,cahier.getCourante());
        cahier.ajouterPage(date2,"Ma naissance2");
        assertEquals(date2,cahier.getCourante());
        cahier.jourPrecedent();
        assertEquals(date,cahier.getCourante());
    }

    @Test
    void changerPage() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);
        DateCahier date2 = new DateCahier(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date,"Ma naissance");
        cahier.ajouterPage(date2,"Ma naissance2");

        cahier.changerPage(date);
        assertEquals(date,cahier.getCourante());


    }


    @Test
    void estDejaDansCahier() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);
        DateCahier date2 = new DateCahier(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date, "Ma naissance");
        assertFalse(cahier.estDejaDansCahier(date2));
        cahier.ajouterPage(date2, "Ma naissance2");
        assertTrue(cahier.estDejaDansCahier(date2));

    }


    @Test
    void nombreDeJours() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);
        DateCahier date2 = new DateCahier(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date, "Ma naissance");
        assertEquals(1,cahier.nombreDeJours());
        cahier.ajouterPage(date2, "Ma naissance2");
        assertEquals(2,cahier.nombreDeJours());

    }

    @Test
    void getPage() throws CahierException {
        Cahier cahier = new Cahier();
        DateCahier date = new DateCahier(2002, Calendar.AUGUST,27);
        DateCahier date2 = new DateCahier(2003, Calendar.AUGUST,27);

        cahier.ajouterPage(date,"Ma naissance");
        cahier.ajouterPage(date2,"Ma naissance2");

        assertEquals("Ma naissance",cahier.getPage(date).getTitre());
        assertEquals("Ma naissance2",cahier.getPage(date2).getTitre());
    }
}