package cahierIG;

import java.util.Iterator;
import java.util.Date;

public class PageJourIG extends PageIG{

    Date dateDuJour;

    public PageJourIG(Date date){
        super();
        this.dateDuJour = date;
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
    public Date getDate()
    {
        return dateDuJour;
    }

    @Override
    public Iterator<NodeIG> iterator() {
        return nodes.values().iterator();
    }
}
