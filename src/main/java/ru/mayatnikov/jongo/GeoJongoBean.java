package ru.mayatnikov.jongo;

import org.apache.commons.logging.Log;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import ru.mayatnikov.datamodel.GeoPoint;
import ru.mayatnikov.datamodel.GeoPointDist;
import ru.mayatnikov.jongo.logger.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 05.04.14
 * Time: 18:03
 */

public class GeoJongoBean  implements  iGeoJongoBean {

    Jongo jongo;
    String collectionName;


    MongoCollection collection;

    private String findGeoNear;        // здесь запрос поиска точек
    Iterable<GeoPointDist> result;

    @Logger
    private Log log;

    /**
     * поиск точек в окружности
     *
     * @param lat  долгота
     * @param apt  широта
     * @param dist расстояние
     * @return Itarable найденных GeoPointDist (точка и расстояние до нее)
     */
    public Iterable<GeoPointDist> findNear(double lat, double apt, double dist) {

        Long dbsz = getDbSize();

        log.debug("Db Collection " + collectionName + " size:" + dbsz + " records");
        System.out.printf("Print: collection [%s] size:%d records \n", collectionName, dbsz);

        result = collection.aggregate(findGeoNear, lat, apt, dist).
                and("{$match:{type:'Feature'}}").                  // доп условия поиска
                and("{$limit:100}").
                as(GeoPointDist.class);
        return result;
    }

    /**
     * @return printable string
     */
    public String toString() {
        int tik = 0;
        StringBuffer s = new StringBuffer();
        for (GeoPoint res : result) {
            tik++;
            s.append(tik + ">[" + res + "]\n");
        }
        return s.toString();
    }

    public Long getDbSize() {
        return collection.count();
    }
/*  setter  & getter */

    public void setJongo(Jongo jongo) {
        this.jongo = jongo;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCollection(MongoCollection collection) {
        this.collection = collection;
    }

    public MongoCollection getCollection() {
        return collection;
    }

    public String getFindGeoNear() {
        return findGeoNear;
    }

    public void setFindGeoNear(String findGeoNear) {
        this.findGeoNear = findGeoNear;
    }
}
