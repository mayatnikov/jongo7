package ru.mayatnikov.datamodel;

import org.bson.types.ObjectId;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 13.04.14
 * Time: 21:06
 */
public class GeoPoint {
    private  ObjectId _id;
    private String type;
    private Integer id;
    private Geometry geometry;
    private Properties properties;

    public String toString() {
        return "Id:"+id+" Type:"+type+" Geometry:"+geometry+" Properties:"+properties;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
/*
{
    "_id" : ObjectId("533d1357ca29c2015a6274a2"),
    "type" : "Feature",
    "id" : 1,
    "geometry" : {
        "type" : "Point",
        "coordinates" : [
            55.6655805402424,
            37.6265535409549
        ]
    },
    "properties" : {
        "address" : "Москва ул. Новикова Прибоя 12-1",
        "ptype" : "ATM",
        "Time1" : "8:30",
        "Time2" : "17:30",
        "orgName" : "Супер Консультации",
        "phone" : "+7(915)987 33 85",
        "imageType" : "cicle",
        "notes" : "Ограниченный доступ для VIP"
    }
}
 */