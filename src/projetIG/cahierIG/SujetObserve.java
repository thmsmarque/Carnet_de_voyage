package cahierIG;
import vues.Observateur;

import java.util.ArrayList;

public class SujetObserve {

    private ArrayList<Observateur> listeobs=new ArrayList<>();
    public void ajouterObservateur(Observateur v){
        this.listeobs.add(v);
    }

    public void notifierObservateurs(){
        for(Observateur o : listeobs){
            o.reagir();
        }
    }
}