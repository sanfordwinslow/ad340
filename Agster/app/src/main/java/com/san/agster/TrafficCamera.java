package com.san.agster;

import java.util.HashMap;
import java.util.Map;

public class TrafficCamera {

    private String cameraLabel;
    private String imageUrl;
    private String type;
    private double[] coords;

    private Map<String, String> baseUrl = new HashMap<String, String>();

    public TrafficCamera(String cameraLabel, String imageUrl, String type, double[] coords) {
        this.cameraLabel = cameraLabel;
        this.imageUrl = imageUrl;
        this.type = type;
        this.coords = coords;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public String getLabel() {
        return this.cameraLabel;
    }

    public double[] getCoords() {
        return this.coords;
    }
}
