package cahierIG;

public class NodeImageIG extends NodeIG{



    public NodeImageIG(){
        super();
    }


    @Override
    public Boolean estTexte() {
        return false;
    }

    @Override
    public Boolean estImage() {
        return true;
    }

    @Override
    public Boolean estGPS() {
        return false;
    }
}
