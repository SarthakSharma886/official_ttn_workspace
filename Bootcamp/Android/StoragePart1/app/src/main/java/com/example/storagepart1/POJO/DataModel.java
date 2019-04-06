package com.example.storagepart1.POJO;

import java.io.Serializable;

public class DataModel implements Serializable {

    private String name,address;
    private long mobileNumber;

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    private int uniqueId;
    private int actionToBePerformed ;


    public int getActionToBePerformed() {
        return actionToBePerformed;
    }

    public void setActionToBePerformed(int actionToBePerformed) {
        this.actionToBePerformed = actionToBePerformed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


}
