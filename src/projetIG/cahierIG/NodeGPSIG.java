package cahierIG;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapLabel;

public class NodeGPSIG extends NodeIG{

    Coordinate coord;

    MapLabel mapLabel;

    public NodeGPSIG(Coordinate coord)
    {
        super();
        this.coord = coord;
    }

    /**
     * Change les coordonnées
     * @param coord nouvelles coordonnées
     */
    public void setCoord(Coordinate coord)
    {
        this.coord = coord;
    }

    /**
     * Retourne les coordonnées
     * @return les coordonnées du GPS
     */
    public Coordinate getCoord()
    {
        return coord;
    }

    /**
     * Change le MapLabel
     * @param mapLabel le nouveau MapLabel
     */
    public void setMapLabel(MapLabel mapLabel)
    {
        this.mapLabel = mapLabel;
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
