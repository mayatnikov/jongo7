package ru.mayatnikov.jongo;

import ru.mayatnikov.datamodel.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 19.04.14
 * Time: 21:52
 */
public class FindPos extends GeoJongoBean implements iGeoJongoBean  {

@Override
public String toString() {
    int tik = 0;
    StringBuffer s = new StringBuffer();
    for (GeoPoint res : result) {
        tik++;
        s.append(tik + ">[" + res.getProperties() + "]\n");
    }
    return s.toString();
}
}
