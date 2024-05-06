package cahierIG;

public class NodeGPSIG extends NodeIG{

    public NodeGPSIG()
    {
        super();
    }

    @Override
    Boolean estTexte() {
        return false;
    }

    @Override
    Boolean estImage() {
        return false;
    }

    @Override
    Boolean estGPS() {
        return true;
    }
}
