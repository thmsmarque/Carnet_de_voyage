package cahierIG;

import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;

public class NodeImageIG extends NodeIG{

    @Expose
    Image image;

    public NodeImageIG(Image image){
        super();
        this.image = image;
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
