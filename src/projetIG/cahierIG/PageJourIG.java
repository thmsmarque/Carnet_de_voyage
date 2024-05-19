package cahierIG;

import exceptions.CahierException;

import java.util.Iterator;
import java.util.Date;

public class PageJourIG extends PageIG{


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
        return this.getDateDuJour();
    }

}
