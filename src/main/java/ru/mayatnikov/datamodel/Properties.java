package ru.mayatnikov.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 13.04.14
 * Time: 21:13
 */
public class Properties {
    private String address;
    private String ptype;
    @JsonProperty("Time1") private String time1;
    @JsonProperty("Time2") private String time2;
    private String orgName;
    private String phone;
    private String imageType;
    private String notes;

    public String toString() {
        return "Address:"+address+" Type:"+ptype+" Time1:"+time1+" Time2:"+time2+" Org.name:"+orgName+" Phone:"+phone+
                " ImageType:"+imageType+" Notes:"+notes;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

/*
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
 */