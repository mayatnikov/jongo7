package ru.mayatnikov.jongo;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import ru.mayatnikov.datamodel.GeoPointDist;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 15.04.14
 * Time: 15:17
 */
public interface iGeoJongoBean {
    public Iterable<GeoPointDist> findNear(double lat, double apt, double dist);
    public Long  getDbSize();
    public void setJongo(Jongo jongo);
    public void setCollectionName(String collectionName);
    public void setCollection(MongoCollection collection);
    public MongoCollection getCollection();
}
