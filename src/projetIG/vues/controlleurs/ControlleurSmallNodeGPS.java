package vues.controlleurs;

import cahierIG.NodeGPSIG;
import com.sothawo.mapjfx.MapView;

import javafx.fxml.FXML;


public class ControlleurSmallNodeGPS {

    @FXML
    MapView mapView;

    NodeGPSIG node;

    public ControlleurSmallNodeGPS(NodeGPSIG node) {

        this.node = node;
    }

    @FXML
    public void initialize() {
        mapView.initialize();
        mapView.setCenter(node.getCoord());
        mapView.setZoom(10);
    }
}
