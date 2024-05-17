package cahierIG;

import exceptions.CahierException;

import java.util.Iterator;
import java.util.Date;

public class PageJourIG extends PageIG{

    DateCahier dateCahierDuJour;

    public PageJourIG(DateCahier date){
        super(date);
    }

    public PageJourIG(DateCahier date, String titre){
        super(date,titre);
    }

    
    @Override
    public Boolean estPageJour() {
        return true;
    }

    @Override
    public Boolean estPageDeGarde() {
        return false;
    }


    /**
     * Retourne la date de cette page
     */
    public DateCahier getDate()
    {
        return dateCahierDuJour;
    }

    @Override
    public Iterator<NodeIG> iterator() {
        return nodes.values().iterator();
    }
}
