package cahierIG;

import exceptions.CahierException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateCahierTest {

    @Test
    void jourSuivant() throws CahierException {
        DateCahier d = new DateCahier(2012,12,12);
        DateCahier d2 = new DateCahier(2012,12,13);

        DateCahier fev = new DateCahier(2012,2,28);
        DateCahier fev2 = new DateCahier(2012,3,1);

        DateCahier dec = new DateCahier(2012,12,31);
        DateCahier dec2 = new DateCahier(2013,1,1);

        assertTrue(d.jourSuivant().equals(d2));
        assertTrue(fev.jourSuivant().equals(fev2));
        assertTrue(dec.jourSuivant().equals(dec2));
    }

    @Test
    void jourPrecedent() throws CahierException {
        DateCahier d = new DateCahier(2012,12,12);
        DateCahier d2 = new DateCahier(2012,12,11);

        DateCahier fev = new DateCahier(2012,2,28);
        DateCahier fev2 = new DateCahier(2012,3,1);

        DateCahier dec = new DateCahier(2012,12,31);
        DateCahier dec2 = new DateCahier(2013,1,1);

        assertTrue(d.jourPrecedent().equals(d2));
        assertTrue(fev2.jourPrecedent().equals(fev));
        assertTrue(dec2.jourPrecedent().equals(dec));
    }
}