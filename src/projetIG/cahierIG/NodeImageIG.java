package cahierIG;

public class NodeImageIG extends NodeIG{

    Image image;

    public NodeImageIG(){
        super();
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
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
