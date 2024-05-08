package vues;

import cahierIG.Cahier;
import cahierIG.PageIG;

public class VuePageJourIG extends VuePageIG implements Observateur{


    public VuePageJourIG(Cahier c, PageIG page) {
        super(c, page);
    }

    @Override
    public void reagir() {

    }
}
