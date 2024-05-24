package cahierIG;

public class NodeGPSIG extends NodeIG{

    public NodeGPSIG()
    {
        super();
    }

    @Override
     public Boolean estTexte() {
        return false;
    }

    @Override
    public Boolean estImage() {
        return false;
    }

    @Override
    public Boolean estGPS() {
        return true;
    }
}
