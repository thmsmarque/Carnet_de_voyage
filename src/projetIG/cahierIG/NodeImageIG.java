package cahierIG;

public class NodeImageIG extends NodeIG{



    public NodeImageIG(){
        super();
    }


    @Override
    Boolean estTexte() {
        return false;
    }

    @Override
    Boolean estImage() {
        return true;
    }

    @Override
    Boolean estGPS() {
        return false;
    }
}
