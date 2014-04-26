package ru.mayatnikov.datamodel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 13.04.14
 * Time: 21:10
 */
public class Geometry {
    private String type;

    private List<Double> coordinates;

    public String toString() {
        return "GeoType:"+type+" Coordonates:[lat:" + coordinates.get(0) + "/apt:" + coordinates.get(1)+"]";
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}

/*

    "geometry" : {
        "type" : "Point",
        "coordinates" : [
            55.6655805402424,
            37.6265535409549
        ]
    }

 */