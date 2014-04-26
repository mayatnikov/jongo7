package ru.mayatnikov.datamodel;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 13.04.14
 * Time: 23:50
 */
public class GeoPointDist extends GeoPoint {
    private Dist dist;

    public Dist getDist() {
        return dist;
    }

    public void setDist(Dist dist) {
        this.dist = dist;
    }
    public String toString() {
        return "Distination:"+this.dist.getCalculated() +" "+super.toString();
    }
}

/*
            "dist" : {
                "calculated" : 0.0004304037560739515,
                "location" : [
                    55.7009257753217,
                    37.7094239552929
                ]
            }

 */