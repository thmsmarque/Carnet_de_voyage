package cahierIG;

import java.util.Iterator;

public class PageJourIG extends PageIG{

    public PageJourIG(){
        super();
    }

    @Override
    public Boolean estPageJour() {
        return true;
    }

    @Override
    public Boolean estPageDeGarde() {
        return false;
    }

    @Override
    public Iterator<NodeIG> iterator() {
        return nodes.values().iterator();
    }
}
