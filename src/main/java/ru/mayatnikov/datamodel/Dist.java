package ru.mayatnikov.datamodel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 13.04.14
 * Time: 23:51
 */
public class Dist {
    private Double calculated;
    private List<Double> location;

    public Double getCalculated() {
        return calculated;
    }

    public void setCalculated(Double calculated) {
        this.calculated = calculated;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
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