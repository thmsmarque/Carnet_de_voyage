package cahierIG;

import java.util.Iterator;

public class PageDeGardeIG extends PageIG{

    public PageDeGardeIG(){
        super();
    }

    @Override
    public Boolean estPageJour() {
        return false;
    }

    @Override
    public Boolean estPageDeGarde() {
        return true;
    }


}
